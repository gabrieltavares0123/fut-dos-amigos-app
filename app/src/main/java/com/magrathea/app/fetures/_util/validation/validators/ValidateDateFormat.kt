package com.magrathea.app.fetures._util.validation.validators

import com.magrathea.app.R
import com.magrathea.app.fetures._util.validation.validation.UiText
import com.magrathea.app.fetures._util.validation.validation.ValidationResult

class ValidateDateFormat : BaseValidator<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.dateCanNotBeBlank),
            )
        }

        if (!isValidLocalDate(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.invalidDateFormat),
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}