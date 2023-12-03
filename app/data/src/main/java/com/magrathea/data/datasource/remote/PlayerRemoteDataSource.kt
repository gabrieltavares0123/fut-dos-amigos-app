package com.magrathea.data.datasource.remote

import com.magrathea.data.model.remote.PlayerDto

interface PlayerRemoteDataSource {
    suspend fun createPlayer(playerDto: PlayerDto)
}