package com.magrathea.app.fetures.loadgames

import com.magrathea.domain.model.GameModel

sealed class LoadPagedGamesViewState {
    object Loading : LoadPagedGamesViewState()
    object Failure : LoadPagedGamesViewState()
    data class Success(val data: Set<GameModel>) : LoadPagedGamesViewState()
}