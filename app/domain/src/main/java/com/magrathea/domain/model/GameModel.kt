package com.magrathea.domain.model

data class GameModel(
    val id: Long?,
    val date: String,
    val street: String,
    val name: String,
    val number: String,
    val city: String,
    val state: String?,
    val enrolledPlayers: MutableSet<PlayerModel> = mutableSetOf(),
    var owner: PlayerModel?
)
