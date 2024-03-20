package com.damian.rickmorty.domain.repository

import com.damian.rickmorty.data.network.model.CharacterDTO
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.model.CharactersPaginated

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<CharactersPaginated>
    suspend fun getCharacterById(characterId: Int): Result<Character>
}