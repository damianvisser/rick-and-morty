package com.damian.rickmorty.presentation.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.damian.rickmorty.R
import com.damian.rickmorty.common.Failure
import com.damian.rickmorty.domain.model.Character
import com.damian.rickmorty.presentation.components.icon.RmIcon
import com.damian.rickmorty.presentation.components.placeholder.rmPlaceholder
import com.damian.rickmorty.presentation.components.textfield.RmTextField
import com.damian.rickmorty.presentation.components.topbar.RmTopBar

@Composable
fun HomeRoute(
    onClickCharacter: (Int) -> Unit,
) = HomeScreen(
    onClickCharacter = onClickCharacter,
)

@Composable
private fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCharacter: (Int) -> Unit,
) {
    viewModel.onClickCharacter.collect { onClickCharacter(it) }

    val characterPagingItems: LazyPagingItems<Character> = viewModel.charactersState
        .collectAsLazyPagingItems()

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = { RmTopBar.Title(R.string.home_title) },
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
        ) {
            HomeContent(
                characterPagingItems = characterPagingItems,
                userInput = state.searchInputText,
                onEvent = { viewModel.onEvent(it) },
            )
        }
    }
}

@Composable
private fun HomeContent(
    characterPagingItems: LazyPagingItems<Character>,
    userInput: String,
    onEvent: (HomeEvent) -> Unit,
) {
    RmTextField.Default(
        value = userInput,
        onValueChange = { onEvent(HomeEvent.OnSearchBarTextChanged(it)) },
        placeholder = stringResource(id = R.string.home_search_bar_placeholder),
        iconStart = RmIcon.search,
        modifier = Modifier.padding(horizontal = 12.dp),
        showError = characterPagingItems.loadState.refresh == LoadState.Error(Failure.NoCharactersFound),
        errorMessage = R.string.home_error_no_characters_found,
    )

    Characters(
        characterPagingItems = characterPagingItems,
        onEvent = onEvent,
    )
}

@Composable
private fun Characters(
    characterPagingItems: LazyPagingItems<Character>,
    onEvent: (HomeEvent) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 24.dp, start = 6.dp, end = 6.dp),
    ) {
        val itemCount = if (characterPagingItems.loadState.refresh == LoadState.Loading) {
            20
        } else characterPagingItems.itemCount

        items(itemCount) {
            Crossfade(
                targetState = characterPagingItems.loadState.refresh,
                label = ""
            ) { loadState ->
                when (loadState) {
                    is LoadState.Loading -> {
                        LoadingCard(modifier = Modifier.padding(6.dp))
                    }

                    else -> {
                        characterPagingItems[it]?.let { character ->
                            CharacterCard(
                                character = character,
                                onEvent = { event -> onEvent(event) },
                                modifier = Modifier.padding(6.dp),
                            )
                        }
                    }
                }
            }
        }

        characterPagingItems.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    items(20) {
                        LoadingCard(modifier = Modifier.padding(6.dp))
                    }
                }

                else -> {} //TODO: Handle errors
            }
        }
    }
}

@Composable
private fun LoadingCard(
    modifier: Modifier = Modifier,
) = Card(
    shape = RoundedCornerShape(6.dp),
    modifier = modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .rmPlaceholder(
                loading = true,
                backgroundColor = Color.Black,
                shimmerColor = Color.Gray,
            ),
    ) {
    }
}

@Composable
private fun CharacterCard(
    character: Character,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) = Card(
    shape = RoundedCornerShape(6.dp),
    colors = CardColors(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor = MaterialTheme.colorScheme.background,
        disabledContentColor = MaterialTheme.colorScheme.onBackground,
    ),
    modifier = modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
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
                            MaterialTheme.colorScheme.secondary.copy(0.5f),
                            MaterialTheme.colorScheme.secondary.copy(0.9f),
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
