package com.magrathea.domain.usecase

import com.magrathea.core.BaseUseCase
import com.magrathea.core.ResourceResult
import com.magrathea.domain.datadefinitions.PlayerRepository
import kotlinx.coroutines.flow.Flow

class CheckIfThereIsLocalPlayerProfileCreatedUseCase(
    private val playerRepository: PlayerRepository,
) : BaseUseCase<Unit, Flow<ResourceResult<Boolean>>> {
    override suspend fun execute(input: Unit): Flow<ResourceResult<Boolean>> {
        TODO("Not yet implemented")
    }
}