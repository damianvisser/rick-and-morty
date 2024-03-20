package com.damian.rickmorty.presentation.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun RmTopBarImpl(
    modifier: Modifier = Modifier,
    toolbarStartItem: @Composable (BoxScope.() -> Unit)? = null,
    toolbarEndItem: @Composable (BoxScope.() -> Unit)? = null,
    toolbarMiddleItem: @Composable (BoxScope.() -> Unit)? = null,
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .height(64.dp)
) {
    if (toolbarStartItem != null) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) { toolbarStartItem() }
    }

    if (toolbarEndItem != null) {
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            toolbarEndItem()
        }
    }

    if (toolbarMiddleItem != null) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            toolbarMiddleItem()
        }
    }
}