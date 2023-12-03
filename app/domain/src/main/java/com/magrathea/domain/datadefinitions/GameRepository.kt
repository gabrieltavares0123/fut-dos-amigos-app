package com.magrathea.domain.datadefinitions

import androidx.paging.PagingData
import com.magrathea.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun createGame(gameModel: GameModel): Flow<Unit>
    fun loadGames(): Flow<PagingData<GameModel>>
}