package com.magrathea.di

import com.magrathea.teamandplayermanagementfeature.domain.CreatePlayerService
import org.koin.dsl.module

val domainModule = module {
    single { com.magrathea.teamandplayermanagementfeature.domain.CreatePlayerService(get()) }
}