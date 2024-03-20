package com.damian.rickmorty.presentation.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.damian.rickmorty.presentation.components.topbar.RmTopBar

@Composable
fun DetailedRoute(
    onClickBack: () -> Unit,
) = DetailedScreen(
    onClickBack = onClickBack,
)

@Composable
private fun DetailedScreen(
    viewModel: DetailedViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { RmTopBar.Back(onClick = onClickBack, title = state.character?.name ?: "") },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = state.character?.name ?: "")
        }
    }
}