package com.damian.rickmorty.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.damian.rickmorty.presentation.ui.navigation.destinations.DetailedDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val characterId = DetailedDestination.argsFrom(savedStateHandle).characterId
}