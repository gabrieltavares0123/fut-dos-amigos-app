package com.magrathea.di

import com.magrathea.teamandplayermanagementfeature.data.remote.CreatePlayerRepository
import com.magrathea.teamandplayermanagementfeature.data.PlayerAPI
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://127.0.0.1:5005")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun providePlayerApi(retrofit: Retrofit): com.magrathea.teamandplayermanagementfeature.data.PlayerAPI =
        retrofit.create(com.magrathea.teamandplayermanagementfeature.data.PlayerAPI::class.java)

    factory { provideRetrofit() }
    factory { providePlayerApi(get()) }
    single { com.magrathea.teamandplayermanagementfeature.data.remote.CreatePlayerRepository(get()) }
}