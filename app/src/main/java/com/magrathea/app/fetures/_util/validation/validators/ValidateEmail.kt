package com.magrathea.app.fetures._util.validation.validators

import com.magrathea.app.R
import com.magrathea.app.fetures._util.validation.validation.UiText
import com.magrathea.app.fetures._util.validation.validation.ValidationResult

class ValidateEmail : BaseValidator<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.emailCanNotBeBlank)
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.thatIsNotAValidEmail)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}