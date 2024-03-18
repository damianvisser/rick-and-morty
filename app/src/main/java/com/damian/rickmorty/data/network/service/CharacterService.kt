package com.damian.rickmorty.data.network.service

import com.damian.rickmorty.data.network.model.GetCharactersResponseDTO

interface CharacterService {
    suspend fun getCharactersPaginated(page: Int): Result<GetCharactersResponseDTO>
}