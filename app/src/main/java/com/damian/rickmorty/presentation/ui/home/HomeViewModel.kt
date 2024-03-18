package com.damian.rickmorty.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    private val _charactersState: MutableStateFlow<PagingData<Character>> = MutableStateFlow(value = PagingData.empty())
    val charactersState: MutableStateFlow<PagingData<Character>> get() = _charactersState

    init {
        getCharactersPaginated()
    }

    private fun getCharactersPaginated() {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(
                config = PagingConfig(pageSize = 40, prefetchDistance = 20),
                pagingSourceFactory = {
                    CharactersPagingSource(getCharactersUseCase)
                }
            ).flow.collect { result ->
                charactersState.update { result }
            }
        }
    }
}