package com.damian.rickmorty.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object DestinationAnimationStyle : DestinationStyle.Animated {

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return when {

            else -> SlideInHorizontallyOpen
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when {

            else -> SlideOutHorizontallyClose
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return when {

            else -> SlideInHorizontallyClose
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return when {
            else -> SlideOutHorizontallyOpen
        }
    }
}

private const val SHORT = 300
private const val IN_DELAY = 150

val SlideInHorizontallyOpen: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(SHORT, delayMillis = IN_DELAY),
        initialOffsetX = { it / 2 }) + fadeIn(animationSpec = tween(SHORT, delayMillis = IN_DELAY))
val SlideOutHorizontallyClose: ExitTransition =
    slideOutHorizontally(animationSpec = tween(SHORT), targetOffsetX = { -it / 2 }) + fadeOut(
        animationSpec = tween(SHORT)
    )
val SlideInHorizontallyClose: EnterTransition =
    slideInHorizontally(
        animationSpec = tween(SHORT, delayMillis = IN_DELAY),
        initialOffsetX = { -it / 2 }) + fadeIn(animationSpec = tween(SHORT, delayMillis = IN_DELAY))
val SlideOutHorizontallyOpen: ExitTransition =
    slideOutHorizontally(animationSpec = tween(SHORT), targetOffsetX = { it / 2 }) + fadeOut(
        animationSpec = tween(SHORT)
    )

val SlideInVerticallyOpen: EnterTransition =
    slideInVertically(animationSpec = tween(SHORT), initialOffsetY = { it })
val SlideOutVerticallyClose: ExitTransition =
    fadeOut(animationSpec = tween(SHORT))
val SlideInVerticallyClose: EnterTransition =
    fadeIn(animationSpec = tween(SHORT))
val SlideOutVerticallyOpen: ExitTransition =
    slideOutVertically(animationSpec = tween(SHORT), targetOffsetY = { it })