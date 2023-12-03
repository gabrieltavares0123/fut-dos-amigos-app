package com.magrathea.data.datasource.remote.api

import com.magrathea.core.ResponseDto
import com.magrathea.data.model.remote.GameDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GameApi {
    @POST("/game")
    suspend fun createGame(@Body gameDto: GameDto)

    @GET("/game")
    suspend fun loadGames(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ResponseDto<List<GameDto>>
}