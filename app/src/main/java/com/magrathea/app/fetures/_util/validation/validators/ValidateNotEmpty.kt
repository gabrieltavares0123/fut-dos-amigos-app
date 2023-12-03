package com.magrathea.app.fetures._util.validation.validators

import com.magrathea.app.R
import com.magrathea.app.fetures._util.validation.validation.UiText
import com.magrathea.app.fetures._util.validation.validation.ValidationResult

class ValidateNotEmpty : BaseValidator<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.textCannotBeBlank)
            )
        }
        if (input.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.textCannotBeEmpty)
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}