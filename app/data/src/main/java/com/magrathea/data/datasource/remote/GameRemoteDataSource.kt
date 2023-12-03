package com.magrathea.data.datasource.remote

import com.magrathea.core.ResponseDto
import com.magrathea.data.model.remote.GameDto

interface GameRemoteDataSource {
    suspend fun createGame(gameDto: GameDto)
    suspend fun loadGames(
        page: Int,
        size: Int
    ): ResponseDto<List<GameDto>>
}