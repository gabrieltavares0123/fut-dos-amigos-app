package com.magrathea.domain.usecase

import com.magrathea.core.BaseUseCase
import com.magrathea.core.ResourceResult
import com.magrathea.domain.datadefinitions.GameRepository
import com.magrathea.domain.model.GameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CreateGameUseCase(
    private val gameRepository: GameRepository,
) : BaseUseCase<GameModel, Flow<ResourceResult<Unit>>> {
    override suspend fun execute(input: GameModel): Flow<ResourceResult<Unit>> {
        return flow {
            emit(ResourceResult.Loading())
            gameRepository.createGame(input)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    emit(ResourceResult.Error(throwable.stackTraceToString()))
                }
                .collect {
                    emit(ResourceResult.Success(it))
                }
        }
    }
}