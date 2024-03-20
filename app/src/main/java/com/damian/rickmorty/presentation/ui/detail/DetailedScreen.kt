package com.damian.rickmorty.presentation.ui.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailedRoute(
    onClickBack: () -> Unit,
) = DetailedScreen(
    onClickBack = onClickBack,
)

@Composable
fun DetailedScreen(
    viewModel: DetailedViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {

}