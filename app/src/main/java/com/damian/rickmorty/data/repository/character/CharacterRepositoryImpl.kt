package com.damian.rickmorty.data.repository.character

import com.damian.rickmorty.data.network.service.CharacterService
import com.damian.rickmorty.domain.model.CharactersPaginated
import com.damian.rickmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterService,
): CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<CharactersPaginated> =
        service.getCharactersPaginated(page)
            .map { result ->
                 CharactersPaginated(
                     prevPage = result.info.prev?.let { page - 1 },
                     nextPage = result.info.next?.let { page + 1 },
                     result = result.results.map { it.toDomain() },
                 )
            }
}