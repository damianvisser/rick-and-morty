package com.damian.rickmorty.domain.repository

import com.damian.rickmorty.domain.model.CharactersPaginated

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<CharactersPaginated>
}