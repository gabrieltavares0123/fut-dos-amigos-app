package com.magrathea.data.datasource.remote

import com.magrathea.data.datasource.remote.api.PlayerApi
import com.magrathea.data.model.remote.PlayerDto

class PlayerRemoteDataSourceImpl(
    private val playerApi: PlayerApi,
) : PlayerRemoteDataSource {
    override suspend fun createPlayer(playerDto: PlayerDto) {
        try {
            playerApi.createPlayer(playerDto)
            playerApi.createPlayer(playerDto)
        } catch (ex: Exception) {
            throw ex
        }
    }
}