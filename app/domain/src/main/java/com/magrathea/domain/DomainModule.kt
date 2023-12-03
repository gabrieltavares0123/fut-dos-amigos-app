package com.magrathea.domain

import com.magrathea.domain.usecase.CreateGameUseCase
import com.magrathea.domain.usecase.CreatePlayerUseCase
import com.magrathea.domain.usecase.LoadLocalPlayerProfileUseCase
import com.magrathea.domain.usecase.LoadPagedGamesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { CreatePlayerUseCase(get()) }
    single { CreateGameUseCase(get()) }
    single { LoadLocalPlayerProfileUseCase(get()) }
    single { LoadPagedGamesUseCase(get()) }
}