package com.magrathea.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.magrathea.data.datasource.remote.GameRemoteDataSource
import com.magrathea.data.mapper.toDto
import com.magrathea.data.repository.pagingsource.GamesPagingSource
import com.magrathea.domain.datadefinitions.GameRepository
import com.magrathea.domain.model.GameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepositoryImpl(
    private val gameRemoteDataSource: GameRemoteDataSource,
) : GameRepository {
    override fun createGame(gameModel: GameModel): Flow<Unit> {
        return flow {
            emit(gameRemoteDataSource.createGame(gameModel.toDto()))
        }
    }

    override fun loadGames(): Flow<PagingData<GameModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = {
                GamesPagingSource(gameRemoteDataSource)
            }
        ).flow
    }
}