package com.damian.rickmorty.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.usecase.GetCharactersUseCase
import com.damian.rickmorty.presentation.util.SingleEventWithContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    val onClickCharacter = SingleEventWithContent<Int>()

    private val _charactersState: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(value = PagingData.empty())
    val charactersState: MutableStateFlow<PagingData<Character>> get() = _charactersState

    private val _state = MutableStateFlow(HomeViewModelState())
    val state: StateFlow<HomeViewModelState> = _state

    init {
        getCharactersPaginated(null)
        updateSearchWithDebounce()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnClickCharacter -> onClickCharacter(event.characterId)
            is HomeEvent.OnSearchBarTextChanged -> onSearchBarTextChanged(event.text)
        }
    }

    private fun onSearchBarTextChanged(inputText: String) {
        _state.update { it.copy(searchInputText = inputText) }
    }

    @OptIn(FlowPreview::class)
    private fun updateSearchWithDebounce() {
        viewModelScope.launch(Dispatchers.IO) {
            _state
                .map { it.searchInputText }
                .debounce(500)
                .collect { getCharactersPaginated(it) }
        }
    }

    private fun onClickCharacter(characterId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            onClickCharacter.send(characterId)
        }
    }

    private fun getCharactersPaginated(filter: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(
                config = PagingConfig(pageSize = 40, prefetchDistance = 20),
                pagingSourceFactory = {
                    CharactersPagingSource(
                        getCharactersUseCase = getCharactersUseCase,
                        filter = filter,
                    )
                }
            ).flow
                .cachedIn(this)
                .collect { result -> charactersState.update { result } }
        }
    }
}