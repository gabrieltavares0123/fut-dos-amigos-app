package com.magrathea.domain.usecase

import androidx.paging.PagingData
import com.magrathea.core.BaseUseCase
import com.magrathea.domain.datadefinitions.GameRepository
import com.magrathea.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

class LoadPagedGamesUseCase(
    private val gameRepository: GameRepository,
) : BaseUseCase<Unit, Flow<PagingData<GameModel>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<GameModel>> {
        return gameRepository.loadGames()
    }
}