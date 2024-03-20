package com.damian.rickmorty.domain.usecase

import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(
        characterId: Int,
    ): Result<Character> = characterRepository.getCharacterById(characterId)
}