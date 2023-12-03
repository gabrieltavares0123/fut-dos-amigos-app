package com.magrathea.app.fetures._util.validation.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)