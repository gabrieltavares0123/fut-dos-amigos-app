package com.magrathea.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.magrathea.data.model.remote.GameDto
import com.magrathea.domain.model.GameModel

fun GameDto.toModel() = GameModel(
    id = this.id,
    date = this.date,
    street = this.street,
    name = this.name,
    number = this.number,
    city = this.city,
    state = this.state,
    enrolledPlayers = this.enrolledPlayers.map { it.toModel() }.toMutableSet(),
    owner = this.owner?.toModel()
)

@RequiresApi(Build.VERSION_CODES.O)
fun GameModel.toDto() = GameDto(
    id = this.id,
    date = this.date,
    street = this.street,
    number = this.number,
    name = this.name,
    city = this.city,
    state = this.state,
    enrolledPlayers = this.enrolledPlayers.map { it.toDto() }.toMutableSet(),
    owner = this.owner?.toDto()
)