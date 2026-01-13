package com.khayal.designsystem.ui.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khayal.designsystem.ui.buttons.ButtonTokens.border
import com.khayal.designsystem.ui.buttons.ButtonTokens.containerColor
import com.khayal.designsystem.ui.buttons.ButtonTokens.contentColor

@Composable
fun AppButton(
    buttonContent: @Composable (RowScope.() -> Unit),
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonRole: ButtonRole = ButtonRole.PRIMARY,
    buttonVariant: ButtonVariant = ButtonVariant.FILLED,
    enabled: Boolean = true
){
    val containerColor = containerColor(buttonRole, buttonVariant)
    val contentColor = contentColor(buttonRole)
    val border = border(buttonRole, buttonVariant)
    val colors = buttonColors(containerColor, contentColor)

    when (buttonVariant) {
        ButtonVariant.FILLED ->
            Button(
                onClick = onClick,
                modifier = modifier.height(56.dp),
                colors = colors,
                enabled = enabled,
                shape = RoundedCornerShape(12.dp)
            ){
                buttonContent()
            }

        ButtonVariant.OUTLINED ->
            OutlinedButton(
                onClick = onClick,
                modifier = modifier.height(56.dp),
                colors = colors,
                border = border,
                enabled = enabled,
                shape = RoundedCornerShape(12.dp)
            ) {
                buttonContent()
            }

        ButtonVariant.TEXT ->
            TextButton(
                onClick = onClick,
                modifier = modifier.height(48.dp),
                colors = colors,
                enabled = enabled
            ) {
                buttonContent()
            }
    }
}