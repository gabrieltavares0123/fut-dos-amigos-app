package com.magrathea.app.fetures.loadgames

sealed class LoadPagedGamesViewEvent {
    object LoadGames : LoadPagedGamesViewEvent()
}