package com.damian.rickmorty.presentation.components.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damian.rickmorty.presentation.theme.RmTheme

object RmTextField {
    @Composable
    fun Default(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        modifier: Modifier = Modifier,
        showError: Boolean = false,
        @StringRes errorMessage: Int? = null,
        iconStart: ImageVector? = null,
    ) = RmTextFieldImpl(
        value = value,
        onValueChange = onValueChange,
        showError = showError,
        errorMessage = errorMessage,
        placeholder = placeholder,
        modifier = modifier,
        iconStart = iconStart,
    )
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun Preview() = RmTheme {
    Column(modifier = Modifier.padding(24.dp)) {
        RmTextField.Default(value = "Default", onValueChange = {}, placeholder = "placeholder")
        RmTextField.Default(value = "", onValueChange = {}, placeholder = "Placeholder")
    }
}