package com.damian.rickmorty.domain.model

import com.damian.rickmorty.data.network.model.Location
import com.damian.rickmorty.data.network.model.Origin

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
)