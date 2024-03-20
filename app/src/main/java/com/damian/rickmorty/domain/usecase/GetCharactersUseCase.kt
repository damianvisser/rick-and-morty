package com.damian.rickmorty.domain.usecase

import com.damian.rickmorty.domain.model.CharactersPaginated
import com.damian.rickmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(page: Int): Result<CharactersPaginated> =
        characterRepository.getCharacters(page = page)
}