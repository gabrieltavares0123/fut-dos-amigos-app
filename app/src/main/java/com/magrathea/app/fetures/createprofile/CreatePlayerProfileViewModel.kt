package com.magrathea.app.fetures.createprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magrathea.app.fetures._util.validation.validators.ValidateNotEmpty
import com.magrathea.app.fetures._util.validation.validators.ValidatePhoneNumber
import com.magrathea.app.fetures.showprofile.ShowPlayerProfileViewModel
import com.magrathea.core.ResourceResult
import com.magrathea.domain.model.PlayerModel
import com.magrathea.domain.usecase.CreatePlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreatePlayerProfileViewModel(
    private val createPlayerUseCase: CreatePlayerUseCase,
) : ViewModel() {
    private val textNotEmptyAndBlankValidator = ValidateNotEmpty()
    private val phoneNumberValidator = ValidatePhoneNumber()

    var createPlayerProfileFormState by mutableStateOf(CreatePlayerProfileFormState())

    private val _createPlayerProfileViewState: MutableStateFlow<CreatePlayerProfileViewState> =
        MutableStateFlow(CreatePlayerProfileViewState.Idle)
    val createPlayerProfileViewState: StateFlow<CreatePlayerProfileViewState> =
        _createPlayerProfileViewState

    fun handleCreatePlayerFormEvents(event: CreatePlayerProfileFormEvent) {
        when (event) {
            is CreatePlayerProfileFormEvent.NameChanged -> {
                createPlayerProfileFormState = createPlayerProfileFormState.copy(name = event.name)
                validateName()
            }

            is CreatePlayerProfileFormEvent.SurnameChanged -> {
                createPlayerProfileFormState =
                    createPlayerProfileFormState.copy(surname = event.surname)
                validateSurname()
            }

            is CreatePlayerProfileFormEvent.PhoneNumberChanged -> {
                createPlayerProfileFormState =
                    createPlayerProfileFormState.copy(phoneNumber = event.phoneNumber)
                validatePhoneNumber()
            }

            is CreatePlayerProfileFormEvent.Submit -> {
                if (validateName() && validateSurname() && validatePhoneNumber()) {
                    createPlayer(
                        PlayerModel(
                            id = null,
                            name = createPlayerProfileFormState.name,
                            surname = createPlayerProfileFormState.surname,
                            phoneNumber = createPlayerProfileFormState.phoneNumber,
                        )
                    )
                }
            }
        }
    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumberResult =
            phoneNumberValidator.execute(createPlayerProfileFormState.phoneNumber)
        createPlayerProfileFormState =
            createPlayerProfileFormState.copy(phoneNumberError = phoneNumberResult.errorMessage)
        return phoneNumberResult.successful
    }

    private fun validateName(): Boolean {
        val nameResult = textNotEmptyAndBlankValidator.execute(createPlayerProfileFormState.name)
        createPlayerProfileFormState =
            createPlayerProfileFormState.copy(nameError = nameResult.errorMessage)
        return nameResult.successful
    }

    private fun validateSurname(): Boolean {
        val surnameResult =
            textNotEmptyAndBlankValidator.execute(createPlayerProfileFormState.surname)
        createPlayerProfileFormState =
            createPlayerProfileFormState.copy(surnameError = surnameResult.errorMessage)
        return surnameResult.successful
    }

    private fun createPlayer(playerModel: PlayerModel) {
        viewModelScope.launch {
            createPlayerUseCase.execute(playerModel)
                .collect { createdPlayerResource ->
                    when (createdPlayerResource) {
                        is ResourceResult.Success -> {
                            Log.d(
                                ShowPlayerProfileViewModel::javaClass.name,
                                "Success"
                            )
                            _createPlayerProfileViewState.value =
                                CreatePlayerProfileViewState.Success(data = createdPlayerResource.data)
                        }

                        is ResourceResult.Error -> {
                            Log.d(
                                ShowPlayerProfileViewModel::javaClass.name,
                                createdPlayerResource.error
                            )
                            _createPlayerProfileViewState.value =
                                CreatePlayerProfileViewState.Failure(error = createdPlayerResource.error)
                        }

                        is ResourceResult.Loading -> {
                            Log.d(
                                ShowPlayerProfileViewModel::javaClass.name,
                                "Loading..."
                            )
                            _createPlayerProfileViewState.value =
                                CreatePlayerProfileViewState.Loading
                        }
                    }
                }
        }
    }
}