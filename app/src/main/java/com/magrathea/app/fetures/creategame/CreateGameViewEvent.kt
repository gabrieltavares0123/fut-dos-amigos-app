package com.magrathea.app.fetures.creategame

sealed class CreateGameViewEvent {
    data class DateChanged(val date: String) : CreateGameViewEvent()
    data class NameChanged(val name: String) : CreateGameViewEvent()
    data class StreetChanged(val street: String) : CreateGameViewEvent()
    data class NumberChanged(val number: String) : CreateGameViewEvent()
    data class CityChanged(val city: String) : CreateGameViewEvent()
    data class StateChanged(val state: String) : CreateGameViewEvent()
    object Submit : CreateGameViewEvent()
}
