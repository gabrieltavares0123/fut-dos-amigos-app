package com.magrathea.data.datasource.local

import com.magrathea.data.datasource.local.database.PlayerDao
import com.magrathea.data.model.local.PlayerEntity

class PlayerLocalDataSourceImpl(
    private val playerDao: PlayerDao,
) : PlayerLocalDataSource {
    override suspend fun createPlayer(playerEntity: PlayerEntity) {
        playerDao.insertLocalPlayer(playerEntity)
    }

    override suspend fun checkIfThereIsALocalPlayerProfile(): Boolean {
        return playerDao.checkIfThereIsALocalPlayerProfile()
    }

    override suspend fun loadLocalPlayerProfile(): PlayerEntity? {
        return playerDao.loadLocalPlayerProfile()
    }
}