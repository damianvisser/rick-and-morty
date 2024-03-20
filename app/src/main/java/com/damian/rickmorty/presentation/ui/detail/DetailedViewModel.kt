package com.damian.rickmorty.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.rickmorty.domain.usecase.GetCharacterByIdUseCase
import com.damian.rickmorty.presentation.ui.navigation.destinations.DetailedDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel() {
    val characterId = DetailedDestination.argsFrom(savedStateHandle).characterId

    private val _state = MutableStateFlow(DetailedViewModelState())
    val state: StateFlow<DetailedViewModelState> = _state


    init {
        getCharacterById()
    }

    private fun getCharacterById() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterByIdUseCase(characterId = characterId)
                .fold(
                    onSuccess = { character ->  _state.update { it.copy(character = character) } },
                    onFailure = {}, //TODO: Handle error state
                )
        }
    }
}