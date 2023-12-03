package com.magrathea.data.model.remote

data class GameDto(
    val id: Long?,
    val date: String,
    val street: String,
    val name: String,
    val number: String,
    val city: String,
    val state: String?,
    val enrolledPlayers: MutableSet<PlayerDto> = mutableSetOf(),
    val owner: PlayerDto?
)