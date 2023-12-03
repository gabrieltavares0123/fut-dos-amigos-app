package com.magrathea.domain.usecase

import com.magrathea.core.BaseUseCase
import com.magrathea.core.ResourceResult
import com.magrathea.domain.datadefinitions.PlayerRepository
import com.magrathea.domain.model.PlayerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CreatePlayerUseCase(
    private val playerRepository: PlayerRepository,
) : BaseUseCase<PlayerModel, Flow<ResourceResult<Unit>>> {
    override suspend fun execute(input: PlayerModel): Flow<ResourceResult<Unit>> {
        return flow {
            emit(ResourceResult.Loading())
            playerRepository.createPlayer(input)
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