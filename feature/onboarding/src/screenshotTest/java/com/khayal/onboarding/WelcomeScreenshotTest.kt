package com.khayal.onboarding

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.khayal.designsystem.theme.FairShareTheme
import com.khayal.test.screenshot.DevicePreviews

/**
 * This is a special preview that will be used for screenshot testing.
 * It is located in the `screenshotTest` source set.
 */
@PreviewTest
@DevicePreviews
@Composable
fun WelcomeScreenshotPreview() {
    FairShareTheme {
        Welcome(onGetStartedClick = {}, onLearnMoreClick = {})
    }
}
