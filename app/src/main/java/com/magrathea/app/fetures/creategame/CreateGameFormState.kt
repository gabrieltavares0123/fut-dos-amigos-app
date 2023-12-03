package com.magrathea.app.fetures.creategame

import com.magrathea.app.fetures._util.validation.validation.UiText


data class CreateGameFormState(
    val date: String = "",
    val dateError: UiText? = null,

    val name: String = "",
    val nameError: UiText? = null,

    val street: String = "",
    val streetError: UiText? = null,

    val number: String = "",
    val numberError: UiText? = null,

    val city: String = "",
    val cityError: UiText? = null,

    val state: String = "",
    val stateError: UiText? = null,
)