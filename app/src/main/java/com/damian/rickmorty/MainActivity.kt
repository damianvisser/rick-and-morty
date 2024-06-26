package com.damian.rickmorty

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

        installSplashScreen().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dismissSplashScreen(this)
            }
        }

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

@RequiresApi(Build.VERSION_CODES.S)
private fun dismissSplashScreen(splashScreen: SplashScreen) {
    splashScreen.setOnExitAnimationListener { splashScreenView ->

        /**
         * There is a bug where splashScreenView.iconView is sometimes null
         * https://issuetracker.google.com/issues/243457485
         */
        val scaleOut = try {
            val scaleOutX = ObjectAnimator.ofFloat(
                splashScreenView.iconView,
                View.SCALE_X,
                1f,
                0f,
            )
            val scaleOutY = ObjectAnimator.ofFloat(
                splashScreenView.iconView,
                View.SCALE_Y,
                1f,
                0f,
            )
            scaleOutX to scaleOutY
        } catch (e: Exception) {
            null
        }

        val fadeOut = ObjectAnimator.ofFloat(
            splashScreenView.view,
            View.ALPHA,
            1f,
            0f,
        )

        fadeOut.startDelay = 250L

        AnimatorSet().apply {
            scaleOut?.let { (scaleOutX, scaleOutY) ->
                playTogether(scaleOutY, scaleOutX, fadeOut)
            } ?: playTogether(fadeOut)

            interpolator = AccelerateDecelerateInterpolator()
            duration = 300L
            start()
        }

    }
}