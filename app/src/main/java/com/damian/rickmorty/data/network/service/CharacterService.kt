package com.damian.rickmorty.data.network.service

import com.damian.rickmorty.data.network.model.CharacterDTO
import com.damian.rickmorty.data.network.model.GetCharactersResponseDTO

interface CharacterService {
    suspend fun getCharactersPaginated(page: Int, filter: String?): Result<GetCharactersResponseDTO>
    suspend fun getCharacterById(characterId: Int): Result<CharacterDTO>
}