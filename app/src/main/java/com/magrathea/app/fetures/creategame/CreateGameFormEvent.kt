package com.magrathea.app.fetures.creategame

sealed class CreateGameFormEvent {
    data class NameChanged(val name: String) : CreateGameFormEvent()
    data class DateChanged(val date: String) : CreateGameFormEvent()
    data class StreetChanged(val street: String) : CreateGameFormEvent()
    data class NumberChanged(val number: String) : CreateGameFormEvent()
    data class CityChanged(val city: String) : CreateGameFormEvent()
    data class StateChanged(val state: String) : CreateGameFormEvent()
    object Submit : CreateGameFormEvent()
}