package com.magrathea.data

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.magrathea.data.datasource.local.PlayerLocalDataSource
import com.magrathea.data.datasource.local.PlayerLocalDataSourceImpl
import com.magrathea.data.datasource.local.database.FutebolDosAmigosDatabase
import com.magrathea.data.datasource.remote.GameRemoteDataSource
import com.magrathea.data.datasource.remote.GameRemoteDataSourceImpl
import com.magrathea.data.datasource.remote.PlayerRemoteDataSource
import com.magrathea.data.datasource.remote.PlayerRemoteDataSourceImpl
import com.magrathea.data.datasource.remote.api.GameApi
import com.magrathea.data.datasource.remote.api.PlayerApi
import com.magrathea.data.repository.GameRepositoryImpl
import com.magrathea.data.repository.PlayerRepositoryImpl
import com.magrathea.domain.datadefinitions.GameRepository
import com.magrathea.domain.datadefinitions.PlayerRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val dataModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.18.9:5005")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setDateFormat("yyyy-MM-dd").create()
                )
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun providePlayerApi(retrofit: Retrofit): PlayerApi =
        retrofit.create(PlayerApi::class.java)

    fun provideCreateGameApi(retrofit: Retrofit): GameApi =
        retrofit.create(GameApi::class.java)

    // Room.
    single<FutebolDosAmigosDatabase> {
        Room.databaseBuilder(
            get(),
            FutebolDosAmigosDatabase::class.java,
            "fut-dos-amigos-db"
        ).build()
    }
    // Daos.
    single {
        val database = get<FutebolDosAmigosDatabase>()
        database.playerDao()
    }

    // Retrofit.
    factory { provideRetrofit() }

    // APIs.
    factory { providePlayerApi(get()) }
    factory { provideCreateGameApi(get()) }

    // Local data sources.
    single<PlayerLocalDataSource> { PlayerLocalDataSourceImpl(get()) }

    // Remote data sources.
    single<GameRemoteDataSource> { GameRemoteDataSourceImpl(get()) }
    single<PlayerRemoteDataSource> { PlayerRemoteDataSourceImpl(get()) }

    // Repositories.
    single<PlayerRepository> { PlayerRepositoryImpl(get(), get()) }
    single<GameRepository> { GameRepositoryImpl(get()) }
}