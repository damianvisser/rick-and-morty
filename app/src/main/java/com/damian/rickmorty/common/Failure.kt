package com.damian.rickmorty.common

sealed class Failure: Throwable() {
    data class NetworkFailure(
        val statusCode: Int,
    ): Failure()

    data object NetworkSerializationFailure: Failure()
}