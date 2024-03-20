package com.damian.rickmorty.presentation.components.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damian.rickmorty.R
import com.damian.rickmorty.presentation.components.button.RmIconButton
import com.damian.rickmorty.presentation.theme.RmTheme

object RmTopBar {
    @Composable
    fun Title(title: Int) = RmTopBarImpl(
        toolbarStartItem = {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 24.dp),
            )
        },
    )

    @Composable
    fun Back(
        onClick: () -> Unit,
        title: String? = null,
        endItem: (@Composable BoxScope.() -> Unit)? = null,
    ) = RmTopBarImpl(
        toolbarStartItem = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(12.dp))

                RmIconButton.BACK(
                    onClick = onClick,
                    modifier = Modifier,
                    enabled = true,
                )

                Spacer(modifier = Modifier.width(24.dp))

                title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge.copy(
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Proportional,
                                trim = LineHeightStyle.Trim.None,
                            ),
                        ),
                    )
                }
            }
        },
        toolbarEndItem = endItem,
    )
}

@Composable
@Preview
private fun PreviewEmpty() = RmTheme {
    RmTopBar.Title(R.string.home_title)
}

@Composable
@Preview
private fun PreviewBack() = RmTheme {
    RmTopBar.Back(onClick = {})
}