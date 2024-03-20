package com.damian.rickmorty.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.damian.rickmorty.presentation.ui.detail.DetailedRoute
import com.damian.rickmorty.presentation.ui.home.HomeRoute
import com.damian.rickmorty.presentation.ui.navigation.destinations.DetailedDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate

@RootNavGraph(start = true)
@Destination(style = DestinationAnimationStyle::class)
@Composable
fun Home(
    navController: NavController,
) = HomeRoute(
    onClickCharacter = { navController.navigate(DetailedDestination(characterId = it)) },
)

@RootNavGraph
@Destination(style = DestinationAnimationStyle::class)
@Composable
fun Detailed(
    navController: NavController,
    characterId: Int,
) = DetailedRoute(
    onClickBack = { navController.navigateUp() }
)