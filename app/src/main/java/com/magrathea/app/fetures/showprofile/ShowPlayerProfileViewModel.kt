package com.magrathea.app.fetures.showprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magrathea.core.ResourceResult
import com.magrathea.domain.model.PlayerModel
import com.magrathea.domain.usecase.LoadLocalPlayerProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowPlayerProfileViewModel(
    private val loadLocalPlayerUseCase: LoadLocalPlayerProfileUseCase,
) : ViewModel() {
    private val _showPlayerProfileViewState: MutableStateFlow<ShowPlayerProfileViewState> =
        MutableStateFlow(ShowPlayerProfileViewState.Idle)
    val showPlayerProfileViewState: StateFlow<ShowPlayerProfileViewState> =
        _showPlayerProfileViewState

    init {
        handleShowPlayerProfileEvents(ShowPlayerProfileViewEvent.LoadPlayerProfile)
    }

    fun handleShowPlayerProfileEvents(event: ShowPlayerProfileViewEvent) {
        when (event) {
            is ShowPlayerProfileViewEvent.LoadPlayerProfile -> {
                loadPlayerProfile()
            }
        }
    }

    private fun loadPlayerProfile() {
        viewModelScope.launch {
            loadLocalPlayerUseCase.execute(Unit).collect { resourceResult ->
                when (resourceResult) {
                    is ResourceResult.Success -> {
                        if (resourceResult.data != null) {
                            Log.i(
                                ShowPlayerProfileViewModel::javaClass.name,
                                "Player profile found."
                            )
                            val playerData = resourceResult.data as PlayerModel
                            _showPlayerProfileViewState.value =
                                ShowPlayerProfileViewState.Success(playerData = playerData)
                        } else {
                            _showPlayerProfileViewState.value =
                                ShowPlayerProfileViewState.CreateANewPlayer
                        }
                    }

                    is ResourceResult.Loading -> {
                        _showPlayerProfileViewState.value = ShowPlayerProfileViewState.Loading
                        Log.i(
                            ShowPlayerProfileViewModel::javaClass.name,
                            "Loading Player profile..."
                        )
                    }

                    is ResourceResult.Error -> {
                        _showPlayerProfileViewState.value =
                            ShowPlayerProfileViewState.Failure(error = resourceResult.error)
                        Log.e(
                            ShowPlayerProfileViewModel::javaClass.name,
                            "Error loading Player profile."
                        )
                    }
                }
            }
        }
    }
}
