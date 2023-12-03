package com.magrathea.app.fetures.createprofile

sealed class CreatePlayerProfileViewState {
    object Idle : CreatePlayerProfileViewState()
    object Loading : CreatePlayerProfileViewState()
    data class Success(val data: Any?) : CreatePlayerProfileViewState()
    data class Failure(val error: String) : CreatePlayerProfileViewState()
}