package com.damian.rickmorty.presentation.components.placeholder

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import nl.labela.virtuakey.core.ui.designsystem.placeholder.PlaceholderHighlight
import nl.labela.virtuakey.core.ui.designsystem.placeholder.placeholder
import nl.labela.virtuakey.core.ui.designsystem.placeholder.shimmer

fun Modifier.rmPlaceholder(
    loading: Boolean,
    backgroundColor: Color,
    shimmerColor: Color,
    shape: Shape = RoundedCornerShape(4.dp),
): Modifier = this.placeholder(
    color = backgroundColor,
    visible = loading,
    shape = shape,
    highlight = PlaceholderHighlight.shimmer(highlightColor = shimmerColor),
)