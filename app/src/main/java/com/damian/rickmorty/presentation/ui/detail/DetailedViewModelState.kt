package com.damian.rickmorty.presentation.ui.detail

import com.damian.rickmorty.domain.model.Character

data class DetailedViewModelState(
    val character: Character? = null,
)