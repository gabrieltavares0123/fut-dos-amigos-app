package com.magrathea.app.fetures._util.validation.validators

import android.util.Patterns

fun isNumber(value: String): Boolean {
    return value.isEmpty() || Regex("^\\d+\$").matches(value)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.any { it.isDigit() } &&
            password.any { it.isLetter() }
}

fun isValidLocalDate(date: String): Boolean {
    return date.isEmpty() || Regex("^\\d{4}-\\d{2}-\\d{2}$").matches(date)
}