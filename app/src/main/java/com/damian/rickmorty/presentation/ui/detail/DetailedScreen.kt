package com.damian.rickmorty.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.damian.rickmorty.domain.model.Character
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
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = { RmTopBar.Back(onClick = onClickBack, title = state.character?.name ?: "") },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp),
        ) {
            DetailedContent(character = state.character)
        }
    }
}

@Composable
private fun DetailedContent(
    character: Character?
) {
    character?.image?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds,
            )
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    KeyValueList(
        items = listOf(
            "Species" to (character?.species ?: ""),
            "Gender" to (character?.gender ?: ""),
            "Status" to (character?.status ?: ""),
            "Location" to (character?.location ?: ""),
            "Origin" to (character?.origin ?: ""),
        ),
    )
}

@Composable
private fun KeyValueList(
    items: List<Pair<String, String>>
) = LazyColumn {
    items(items) { (key, value) ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = key,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(100.dp),
            )
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}