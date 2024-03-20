package com.damian.rickmorty.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDTO,
    val name: String,
    val origin: OriginDTO,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
) {
    @Serializable
    data class LocationDTO(
        val name: String,
        val url: String
    )

    @Serializable
    data class OriginDTO(
        val name: String,
        val url: String
    )
}