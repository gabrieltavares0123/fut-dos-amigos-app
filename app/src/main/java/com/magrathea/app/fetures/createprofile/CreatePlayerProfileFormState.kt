package com.magrathea.app.fetures.createprofile

import com.magrathea.app.fetures._util.validation.validation.UiText

data class CreatePlayerProfileFormState(
    val name: String = "",
    val nameError: UiText? = null,

    val surname: String = "",
    val surnameError: UiText? = null,

    val phoneNumber: String = "",
    val phoneNumberError: UiText? = null,
)