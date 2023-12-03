package com.magrathea.core

import com.google.gson.annotations.SerializedName

data class ResponseDto<T : Any>(
    @SerializedName("content")
    val content: T,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("totalPages")
    val totalPages: Int? = null,
    @SerializedName("totalElements")
    val totalElements: Long? = null,
    @SerializedName("prevPage")
    val prevPage: Int? = null,
    @SerializedName("nextPage")
    val nextPage: Int? = null,
)