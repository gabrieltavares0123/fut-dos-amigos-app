package com.magrathea.app.fetures.creategame

sealed class CreateGameViewState {
    object Idle : CreateGameViewState()
    object Loading : CreateGameViewState()
    data class Success(val data: Any?) : CreateGameViewState()
    data class Error(val error: String) : CreateGameViewState()
}