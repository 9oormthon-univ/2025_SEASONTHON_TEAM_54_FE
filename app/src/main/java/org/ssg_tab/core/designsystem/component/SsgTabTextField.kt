package org.ssg_tab.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewSsgTabTextField() {
    SsgTabTheme {
        SsgTabTextField(
            textValue = "",
            placeHolder = "이메일을 입력해주세요",
            onTextChanged = {},
            isPassword = false,
        )
    }
}

@Composable
fun SsgTabTextField(
    textValue: String,
    placeHolder: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    suffix: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester? = null,
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = SsgTabTheme.colors.LightGray,
        backgroundColor = SsgTabTheme.colors.LightGray,
    )

    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = onTextChanged,
            cursorBrush = SolidColor(SsgTabTheme.colors.LightGray,),
            singleLine = true,
            textStyle = SsgTabTheme.typography.Regular_R,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = focusRequester?.let { modifier.focusRequester(it) } ?: modifier,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SsgTabTheme.colors.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = SsgTabTheme.colors.MidGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (textValue.isEmpty()) {
                                Text(
                                    text = placeHolder,
                                    color = SsgTabTheme.colors.LightGray,
                                    style = SsgTabTheme.typography.Regular_R
                                )
                            }
                            innerTextField()
                        }

                        suffix?.invoke()
                    }
                }
            },
        )
    }
}