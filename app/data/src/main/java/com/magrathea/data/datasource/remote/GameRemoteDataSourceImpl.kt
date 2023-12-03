package com.magrathea.data.datasource.remote

import com.magrathea.core.ResponseDto
import com.magrathea.data.datasource.remote.api.GameApi
import com.magrathea.data.model.remote.GameDto

class GameRemoteDataSourceImpl(
    private val gameApi: GameApi,
) : GameRemoteDataSource {
    override suspend fun createGame(gameDto: GameDto) {
        gameApi.createGame(gameDto)
    }

    override suspend fun loadGames(
        page: Int,
        size: Int,
    ): ResponseDto<List<GameDto>> {
        return gameApi.loadGames(page, size)
    }
}