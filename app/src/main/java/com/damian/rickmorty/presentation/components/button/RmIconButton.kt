package com.damian.rickmorty.presentation.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.damian.rickmorty.presentation.components.icon.RmIcon

enum class RmIconButton {
    BACK;

    private val icon
        get() = when (this) {
            BACK -> RmIcon.arrowBack
        }

    private fun Modifier.applySize(): Modifier = when (this@RmIconButton) {
        BACK -> {
            this
                .size(48.dp)
        }
    }

    private val iconSize
        get() = when (this) {
            BACK -> 24.dp
        }

    @Composable
    operator fun invoke(
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean? = true
    ) {
        IconButtonImpl(
            icon = icon,
            iconSize = iconSize,
            onClick = onClick,
            enabled = enabled ?: true,
            modifier = modifier.applySize()
        )
    }
}

@Composable
fun IconButtonImpl(
    icon: ImageVector,
    iconSize: Dp,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier
) = IconButton(
    onClick = onClick,
    enabled = enabled,
    modifier = modifier
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(iconSize)
    )
}