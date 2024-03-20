package com.damian.rickmorty.presentation.ui.home

sealed class HomeEvent {
    data class OnClickCharacter(val characterId: Int): HomeEvent()
    data class OnSearchBarTextChanged(val text: String): HomeEvent()
}