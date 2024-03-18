package com.damian.rickmorty.domain.model

data class CharactersPaginated(
    val prevPage: Int?,
    val nextPage: Int?,
    val result: List<Character>,
)