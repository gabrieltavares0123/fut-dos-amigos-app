package com.magrathea.data.mapper

import com.magrathea.data.model.local.PlayerEntity
import com.magrathea.data.model.remote.PlayerDto
import com.magrathea.domain.model.PlayerModel

fun PlayerDto.toModel() =
    PlayerModel(
        id = this.id,
        name = this.name,
        surname = this.name,
        phoneNumber = this.phoneNumber,
    )

fun PlayerModel.toDto() =
    PlayerDto(
        id = this.id,
        name = this.name,
        surname = this.name,
        phoneNumber = this.phoneNumber,
    )

fun PlayerModel.toEntity() =
    PlayerEntity(
        id = this.id,
        name = this.name,
        surname = this.surname,
        phoneNumber = this.phoneNumber
    )

fun PlayerEntity.toModel() =
    PlayerModel(
        id = this.id,
        name = this.name,
        surname = this.surname,
        phoneNumber = this.phoneNumber
    )
