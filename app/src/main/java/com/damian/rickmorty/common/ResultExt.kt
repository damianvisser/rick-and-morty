package com.damian.rickmorty.common

/**
 * This function unwraps result and returns the success object "T".
 * In the block you should handle your failure case which exposes a Throwable.
 */
inline fun <T> Result<T>.failed(block: (Throwable) -> Nothing): T = this.fold(
    onSuccess = { return it },
    onFailure = { block(it) },
)