package com.magrathea.core

sealed class ResourceResult<T> {
    data class Success<T>(val data: T) : ResourceResult<T>()
    data class Error<T>(val error: String) : ResourceResult<T>()
    class Loading<T> : ResourceResult<T>()
}