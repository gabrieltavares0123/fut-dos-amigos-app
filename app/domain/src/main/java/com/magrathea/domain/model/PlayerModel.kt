package com.magrathea.domain.model

data class PlayerModel(
    val id: Long? = null,
    val name: String,
    val surname: String? = null,
    val phoneNumber: String,
)