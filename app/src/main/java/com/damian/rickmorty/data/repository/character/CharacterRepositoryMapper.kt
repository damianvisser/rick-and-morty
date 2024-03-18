package com.damian.rickmorty.data.repository.character

import com.damian.rickmorty.data.network.model.CharacterDTO
import com.damian.rickmorty.domain.model.Character

fun CharacterDTO.toDomain(): Character = Character(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    location = location,
    name = name,
    origin = origin,
    species = species,
    status = status,
    type = type,
    url = url,
)