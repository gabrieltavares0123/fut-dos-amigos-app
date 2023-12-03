package com.magrathea.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey
    val id: Long? = null,
    val name: String,
    val surname: String? = null,
    val phoneNumber: String,
)