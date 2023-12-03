package com.magrathea.app.fetures.showprofile

import com.magrathea.domain.model.PlayerModel

sealed class ShowPlayerProfileViewState {
    object Idle : ShowPlayerProfileViewState()
    data class Success(val playerData: PlayerModel) : ShowPlayerProfileViewState()
    object Loading : ShowPlayerProfileViewState()
    data class Failure(val error: String) : ShowPlayerProfileViewState()
    object CreateANewPlayer : ShowPlayerProfileViewState()
}