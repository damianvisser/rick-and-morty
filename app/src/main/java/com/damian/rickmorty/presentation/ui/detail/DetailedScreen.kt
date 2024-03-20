package com.damian.rickmorty.presentation.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.damian.rickmorty.presentation.components.topbar.RmTopBar

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
    Scaffold(
        topBar = { RmTopBar.Back(onClick = onClickBack) },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }
}