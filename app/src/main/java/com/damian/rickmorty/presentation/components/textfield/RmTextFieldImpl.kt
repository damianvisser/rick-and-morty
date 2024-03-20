package com.damian.rickmorty.presentation.components.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.damian.rickmorty.presentation.theme.RmTheme

@Composable
fun RmTextFieldImpl(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    showError: Boolean = false,
    @StringRes errorMessage: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    iconStart: ImageVector? = null
) {
    var focused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(
                width = 1.dp,
                color = if (showError) {
                    MaterialTheme.colorScheme.error
                } else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    iconStart?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                        )
                    }

                    TextField(
                        value = value,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        onValueChange = onValueChange,
                        placeholder = placeholder?.let { { Placeholder(placeholder = placeholder) } },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            errorCursorColor = MaterialTheme.colorScheme.error,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        singleLine = true,
                        keyboardOptions = keyboardOptions,
                        visualTransformation = visualTransformation,
                        modifier = Modifier
                            .onFocusChanged { focused = it.isFocused }
                            .fillMaxWidth()
                    )
                }
            }
        }

        AnimatedContent(showError, label = "") { show ->
            if (show) {
                Text(
                    text = errorMessage?.let { stringResource(id = it) } ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .padding(top = 4.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun Placeholder(placeholder: String) = RmTheme {
    Text(
        text = placeholder,
        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.surfaceTint),
    )
}