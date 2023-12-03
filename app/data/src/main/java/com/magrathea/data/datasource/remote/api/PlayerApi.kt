package com.magrathea.data.datasource.remote.api

import com.magrathea.data.model.remote.PlayerDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PlayerApi {
    @POST("/player")
    suspend fun createPlayer(@Body playerDto: PlayerDto)
}