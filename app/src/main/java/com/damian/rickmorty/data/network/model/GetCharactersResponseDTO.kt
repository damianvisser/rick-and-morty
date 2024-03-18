package com.damian.rickmorty.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class GetCharactersResponseDTO(
    val info: Info,
    val results: List<CharacterDTO>
) {
    @Serializable
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}