package com.damian.rickmorty.data.repository.character

import com.damian.rickmorty.data.network.service.CharacterService
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.model.CharactersPaginated
import com.damian.rickmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterService,
) : CharacterRepository {
    override suspend fun getCharacters(
        page: Int,
        filter: String?,
    ): Result<CharactersPaginated> = service.getCharactersPaginated(page = page, filter = filter)
        .map { result ->
            CharactersPaginated(
                prevPage = result.info.prev?.let { page - 1 },
                nextPage = result.info.next?.let { page + 1 },
                result = result.results.map { it.toDomain() },
            )
        }

    override suspend fun getCharacterById(
        characterId: Int,
    ): Result<Character> = service.getCharacterById(characterId)
        .map { character -> character.toDomain() }
}