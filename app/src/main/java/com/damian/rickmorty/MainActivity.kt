package com.damian.rickmorty

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.damian.rickmorty.presentation.theme.RmTheme
import com.damian.rickmorty.presentation.ui.navigation.NavGraphs
import com.damian.rickmorty.presentation.ui.navigation.destinations.HomeDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val navHostEngine = rememberNavHostEngine()

            val darkThemeEnabled = isSystemInDarkTheme()

            DisposableEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkThemeEnabled },
                    navigationBarStyle = SystemBarStyle.auto(
                        Color.argb(0x80, 0x1b, 0x1b, 0x1b), // Default Android color
                        Color.argb(0xe6, 0xFF, 0xFF, 0xFF), // Default Android color
                    ) { darkThemeEnabled },
                )
                onDispose {}
            }

            RmTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    // Give color to background of activity to match background of composable screens.
                    // Prevents flashing of white color on dark mode when navigating.
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navHostEngine,
                        startRoute = HomeDestination,
                    )
                }
            }
        }
    }
}