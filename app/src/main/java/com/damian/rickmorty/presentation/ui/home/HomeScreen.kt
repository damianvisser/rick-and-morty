package com.damian.rickmorty.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.damian.rickmorty.R
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.presentation.components.topbar.RmTopBar

@Composable
fun HomeRoute(
    onClickCharacter: (Int) -> Unit,
) = HomeScreen(
    onClickCharacter = onClickCharacter,
)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCharacter: (Int) -> Unit,
) {
    viewModel.onClickCharacter.collect { onClickCharacter(it) }

    val characterPagingItems: LazyPagingItems<Character> = viewModel.charactersState
        .collectAsLazyPagingItems()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = { RmTopBar.Title(R.string.home_title) },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to MaterialTheme.colorScheme.inversePrimary,
                            1f to MaterialTheme.colorScheme.primary,
                        ),
                    )
                )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 24.dp, horizontal = 6.dp),
            ) {
                items(characterPagingItems.itemCount) {
                    characterPagingItems[it]?.let { character ->
                        CharacterCard(
                            character = character,
                            onEvent = { event -> viewModel.onEvent(event) },
                            modifier = Modifier.padding(6.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterCard(
    character: Character,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) = Card(
    shape = RoundedCornerShape(6.dp),
    modifier = modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onEvent(HomeEvent.OnClickCharacter(character.id)) },
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(0.3f),
                            MaterialTheme.colorScheme.background.copy(0.8f),
                        )
                    )
                )
        ) {
            Text(
                text = character.name,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}