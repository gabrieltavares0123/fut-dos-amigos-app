package com.magrathea.data.model.remote

import com.google.gson.annotations.SerializedName

data class PlayerDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
)