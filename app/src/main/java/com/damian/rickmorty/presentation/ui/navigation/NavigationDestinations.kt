package com.damian.rickmorty.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.damian.rickmorty.presentation.ui.detail.DetailedRoute
import com.damian.rickmorty.presentation.ui.home.HomeRoute
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination(style = DestinationAnimationStyle::class)
@Composable
fun Home(
    navController: NavController,
) = HomeRoute()

@RootNavGraph
@Destination(style = DestinationAnimationStyle::class)
@Composable
fun Detailed(navController: NavController) = DetailedRoute()