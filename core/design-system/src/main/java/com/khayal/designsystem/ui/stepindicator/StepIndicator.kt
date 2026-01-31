package com.khayal.designsystem.ui.stepindicator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val STEP_INDICATOR_TAG = "stepIndicator"

@Composable
fun StepIndicator(
    currentStep: Int, stepCount: Int, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.testTag(STEP_INDICATOR_TAG),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(stepCount) { index ->
            val isSelected = index == currentStep
            val width by animateDpAsState(targetValue = if (isSelected) 16.dp else 8.dp)
            Box(
                modifier = Modifier
                    .width(width)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
            )
        }
    }
}