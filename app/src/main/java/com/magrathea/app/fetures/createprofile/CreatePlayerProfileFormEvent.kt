package com.magrathea.app.fetures.createprofile

sealed class CreatePlayerProfileFormEvent {
    data class NameChanged(val name: String) : CreatePlayerProfileFormEvent()
    data class SurnameChanged(val surname: String) : CreatePlayerProfileFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : CreatePlayerProfileFormEvent()
    object Submit : CreatePlayerProfileFormEvent()
}