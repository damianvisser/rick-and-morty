package com.damian.rickmorty.domain.usecase

import com.damian.rickmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(page: Int) =
        characterRepository.getCharacters(page = page)
}