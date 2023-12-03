package com.magrathea.data.repository

import com.magrathea.data.datasource.local.PlayerLocalDataSource
import com.magrathea.data.datasource.remote.PlayerRemoteDataSource
import com.magrathea.data.mapper.toDto
import com.magrathea.data.mapper.toEntity
import com.magrathea.data.mapper.toModel
import com.magrathea.domain.datadefinitions.PlayerRepository
import com.magrathea.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerRepositoryImpl(
    private val playerRemoteDataSource: PlayerRemoteDataSource,
    private val playerLocalDataSource: PlayerLocalDataSource,
) : PlayerRepository {
    override fun createPlayer(playerModel: PlayerModel): Flow<Unit> {
        return flow {
            emit(playerRemoteDataSource.createPlayer(playerModel.toDto()))
            emit(playerLocalDataSource.createPlayer(playerModel.toEntity()))
        }
    }

    override fun loadLocalPlayerProfile(): Flow<PlayerModel?> {
        return flow {
            emit(playerLocalDataSource.loadLocalPlayerProfile()?.toModel())
        }
    }

    override fun checkIfThereIsALocalPlayerProfile(): Flow<Boolean> {
        return flow {
            emit(playerLocalDataSource.checkIfThereIsALocalPlayerProfile())
        }
    }
}