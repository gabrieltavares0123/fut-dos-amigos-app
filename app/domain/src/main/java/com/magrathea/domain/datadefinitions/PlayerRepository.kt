package com.magrathea.domain.datadefinitions

import com.magrathea.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun createPlayer(playerModel: PlayerModel): Flow<Unit>
    fun loadLocalPlayerProfile(): Flow<PlayerModel?>
    fun checkIfThereIsALocalPlayerProfile(): Flow<Boolean>
}