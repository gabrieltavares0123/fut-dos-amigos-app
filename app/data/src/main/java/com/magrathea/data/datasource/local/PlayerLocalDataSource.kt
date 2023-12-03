package com.magrathea.data.datasource.local

import com.magrathea.data.model.local.PlayerEntity

interface PlayerLocalDataSource {
    suspend fun createPlayer(playerEntity: PlayerEntity)
    suspend fun checkIfThereIsALocalPlayerProfile(): Boolean
    suspend fun loadLocalPlayerProfile(): PlayerEntity?
}