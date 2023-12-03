package com.magrathea.core

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}