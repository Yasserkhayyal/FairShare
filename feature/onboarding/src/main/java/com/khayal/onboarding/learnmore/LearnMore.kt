package com.khayal.onboarding.learnmore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.khayal.designsystem.theme.fairShareTypography
import com.khayal.designsystem.ui.buttons.AppButton
import com.khayal.designsystem.ui.buttons.ButtonRole
import com.khayal.designsystem.ui.buttons.ButtonVariant
import com.khayal.designsystem.ui.stepindicator.StepIndicator
import com.khayal.onboarding.R

const val LEARN_MORE_ILLUSTRATION_ICON_TAG = "learnMoreIllustration"
const val SKIP_BUTTON_TAG = "skipButton"
const val NEXT_BUTTON_TAG = "nextButton"

@Composable
fun LearnMore(
    onSkipButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp, top = 32.dp, end = 24.dp, bottom = 24.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(56.dp)
                        .testTag(LEARN_MORE_ILLUSTRATION_ICON_TAG),
                    imageVector = Icons.Filled.People,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = stringResource(R.string.cd_learn_more_illustration)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.learn_more_title),
                style = MaterialTheme.fairShareTypography.brandAppTitle,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.learn_more_description),
                style = MaterialTheme.fairShareTypography.bodySecondary,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            StepIndicator(currentStep = 0, stepCount = 3)
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppButton(
                    modifier = Modifier.testTag(SKIP_BUTTON_TAG),
                    buttonRole = ButtonRole.SECONDARY,
                    buttonVariant = ButtonVariant.TEXT,
                    onClick = onSkipButtonClicked,
                    buttonContent = {
                        Text(
                            text = stringResource(R.string.skip),
                            style = MaterialTheme.fairShareTypography.buttonLabel,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )

                AppButton(
                    modifier = Modifier.testTag(NEXT_BUTTON_TAG),
                    buttonRole = ButtonRole.PRIMARY,
                    buttonVariant = ButtonVariant.FILLED,
                    onClick = onNextButtonClicked,
                    buttonContent = {
                        Text(
                            text = stringResource(R.string.next),
                            style = MaterialTheme.fairShareTypography.buttonLabel,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        }
    }
}