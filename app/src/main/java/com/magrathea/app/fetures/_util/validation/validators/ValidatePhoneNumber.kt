package com.magrathea.app.fetures._util.validation.validators

import com.magrathea.app.R
import com.magrathea.app.fetures._util.validation.validation.UiText
import com.magrathea.app.fetures._util.validation.validation.ValidationResult

class ValidatePhoneNumber : BaseValidator<String, ValidationResult> {

    override fun execute(input: String): ValidationResult {
        if (!isNumber(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.phoneNumberShouldBeContentJustDigit),
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}