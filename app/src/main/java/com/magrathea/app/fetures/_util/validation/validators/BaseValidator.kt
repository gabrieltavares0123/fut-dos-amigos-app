package com.magrathea.app.fetures._util.validation.validators

interface BaseValidator<In, Out> {
    fun execute(input: In): Out
}