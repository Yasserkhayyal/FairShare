package com.khayal.designsystem.ui.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ButtonTokens {

    @Composable
    fun containerColor(role: ButtonRole, variant: ButtonVariant): Color =
        when (variant) {
            ButtonVariant.FILLED -> when (role) {
                ButtonRole.PRIMARY -> MaterialTheme.colorScheme.primary
                ButtonRole.SECONDARY -> MaterialTheme.colorScheme.surfaceVariant
                ButtonRole.DESTRUCTIVE -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.surface
            }

            else -> Color.Transparent
        }

    @Composable
    fun contentColor(role: ButtonRole): Color =
        when (role) {
            ButtonRole.PRIMARY -> MaterialTheme.colorScheme.onPrimary
            ButtonRole.SECONDARY -> MaterialTheme.colorScheme.onSurface
            ButtonRole.DESTRUCTIVE -> MaterialTheme.colorScheme.onError
            else -> MaterialTheme.colorScheme.onSurface
        }

    @Composable
    fun border(role: ButtonRole, variant: ButtonVariant): BorderStroke? =
        if (variant == ButtonVariant.OUTLINED) {
            BorderStroke(
                1.dp,
                when (role) {
                    ButtonRole.PRIMARY -> MaterialTheme.colorScheme.primary
                    ButtonRole.DESTRUCTIVE -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.outline
                }
            )
        } else null
}
