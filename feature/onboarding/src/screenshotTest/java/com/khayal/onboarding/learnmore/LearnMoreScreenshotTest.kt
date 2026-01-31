package com.khayal.onboarding.learnmore

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.khayal.designsystem.theme.FairShareTheme
import com.khayal.test.screenshot.DevicePreviews

@PreviewTest
@DevicePreviews
@Composable
fun LearnMorePreview() {
    FairShareTheme {
        LearnMore(
            onSkipButtonClicked = {},
            onNextButtonClicked = {},
            learnMoreTitle = "This is a very very very long Learn More Title",
            learnMoreDescription = "This is a very very very very very long Learn More Description..."
        )
    }
}