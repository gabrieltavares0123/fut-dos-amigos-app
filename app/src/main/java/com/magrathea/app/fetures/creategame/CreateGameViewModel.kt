package com.magrathea.app.fetures.creategame

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magrathea.app.fetures._util.validation.validators.ValidateDateFormat
import com.magrathea.app.fetures._util.validation.validators.ValidateNotEmpty
import com.magrathea.app.fetures._util.validation.validators.ValidateNumber
import com.magrathea.app.fetures.showprofile.ShowPlayerProfileViewModel
import com.magrathea.core.ResourceResult
import com.magrathea.domain.model.GameModel
import com.magrathea.domain.model.PlayerModel
import com.magrathea.domain.usecase.CreateGameUseCase
import com.magrathea.domain.usecase.LoadLocalPlayerProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateGameViewModel(
    private val createGameUseCase: CreateGameUseCase,
    private val loadLocalPlayerProfileUseCase: LoadLocalPlayerProfileUseCase,
) : ViewModel() {

    private val validateNotEmpty = ValidateNotEmpty()
    private val validateDateFormat = ValidateDateFormat()
    private val validateNumber = ValidateNumber()

    var createGameFormState by mutableStateOf(CreateGameFormState())

    private val _createGameViewState: MutableStateFlow<CreateGameViewState> =
        MutableStateFlow(CreateGameViewState.Idle)
    val createGameViewState: StateFlow<CreateGameViewState> = _createGameViewState

    fun handleCreateGameFormEvents(event: CreateGameFormEvent) {
        when (event) {
            is CreateGameFormEvent.DateChanged -> {
                createGameFormState = createGameFormState.copy(date = event.date)
                validateDate()
            }

            is CreateGameFormEvent.NameChanged -> {
                createGameFormState = createGameFormState.copy(name = event.name)
                validateName()
            }

            is CreateGameFormEvent.CityChanged -> {
                createGameFormState = createGameFormState.copy(city = event.city)
                validateCity()
            }

            is CreateGameFormEvent.NumberChanged -> {
                createGameFormState = createGameFormState.copy(number = event.number)
                validateNumber()
            }

            is CreateGameFormEvent.StateChanged -> {
                createGameFormState = createGameFormState.copy(state = event.state)
                validateState()
            }

            is CreateGameFormEvent.StreetChanged -> {
                createGameFormState = createGameFormState.copy(state = event.street)
                validateStreet()
            }

            is CreateGameFormEvent.Submit -> {
                if (validateDate() && validateCity() && validateState() && validateNumber() && validateName() && validateStreet()) {
                    createGame(
                        GameModel(
                            id = null,
                            date = createGameFormState.date,
                            street = createGameFormState.street,
                            number = createGameFormState.number,
                            name = createGameFormState.name,
                            city = createGameFormState.street,
                            state = createGameFormState.state,
                            enrolledPlayers = mutableSetOf(),
                            owner = null // Set app player as owner is made in method createGame().
                        )
                    )
                }
            }
        }
    }

    private fun validateDate(): Boolean {
        val dateFormatValidResult = validateDateFormat.execute(createGameFormState.date)
        createGameFormState = createGameFormState.copy(dateError = dateFormatValidResult.errorMessage)
        return dateFormatValidResult.successful
    }


    private fun validateName(): Boolean {
        val nameResult = validateNotEmpty.execute(createGameFormState.name)
        createGameFormState = createGameFormState.copy(nameError = nameResult.errorMessage)
        return nameResult.successful
    }


    private fun validateCity(): Boolean {
        val cityResult = validateNotEmpty.execute(createGameFormState.city)
        createGameFormState = createGameFormState.copy(cityError = cityResult.errorMessage)
        return cityResult.successful
    }


    private fun validateNumber(): Boolean {
        val numberResult = validateNumber.execute(createGameFormState.number)
        createGameFormState = createGameFormState.copy(numberError = numberResult.errorMessage)
        return numberResult.successful
    }

    private fun validateState(): Boolean {
        val stateResult = validateNotEmpty.execute(createGameFormState.state)
        createGameFormState = createGameFormState.copy(stateError = stateResult.errorMessage)
        return stateResult.successful
    }

    private fun validateStreet(): Boolean {
        val streetResult = validateNotEmpty.execute(createGameFormState.name)
        createGameFormState = createGameFormState.copy(streetError = streetResult.errorMessage)
        return streetResult.successful
    }

    private fun createGame(gameModel: GameModel) {
        viewModelScope.launch {
            loadLocalPlayerProfileUseCase.execute(Unit).collect { ownerResult ->
                when (ownerResult) {
                    is ResourceResult.Success -> {
                        gameModel.owner = ownerResult.data as PlayerModel
                        Log.d(
                            CreateGameViewModel::class.java.name,
                            "Success: Game owner added."
                        )
                    }

                    is ResourceResult.Error -> {
                        Log.e(CreateGameViewModel::class.java.name, ownerResult.error)
                    }

                    is ResourceResult.Loading -> {
                        Log.d(
                            CreateGameViewModel::class.java.name,
                            "Loading game owner..."
                        )
                        _createGameViewState.value = CreateGameViewState.Loading
                    }
                }
            }
            createGameUseCase.execute(gameModel).collect { createGameResult ->
                when (createGameResult) {
                    is ResourceResult.Success -> {
                        Log.d(
                            ShowPlayerProfileViewModel::class.java.name,
                            "-> Succeess: Game ${createGameResult.data} created."
                        )
                        _createGameViewState.value =
                            CreateGameViewState.Success(createGameResult.data)
                    }

                    is ResourceResult.Error -> {
                        Log.e(CreateGameViewModel::class.java.name, createGameResult.error)
                        _createGameViewState.value =
                            CreateGameViewState.Error(error = createGameResult.error)
                    }

                    is ResourceResult.Loading -> {
                        Log.d(
                            CreateGameViewModel::class.java.name,
                            "Creating game..."
                        )
                        _createGameViewState.value = CreateGameViewState.Loading
                    }
                }
            }
        }
    }
}