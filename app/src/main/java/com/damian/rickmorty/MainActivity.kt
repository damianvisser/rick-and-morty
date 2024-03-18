package com.damian.rickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.damian.rickmorty.presentation.theme.RickAndMortyApplicationTheme
import com.damian.rickmorty.presentation.ui.navigation.NavGraphs
import com.damian.rickmorty.presentation.ui.navigation.destinations.HomeDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navHostEngine = rememberNavHostEngine()

            RickAndMortyApplicationTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine,
                    startRoute = HomeDestination
                )
            }
        }
    }
}