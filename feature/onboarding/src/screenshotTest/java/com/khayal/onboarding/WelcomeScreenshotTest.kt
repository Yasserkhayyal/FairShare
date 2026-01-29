package com.khayal.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.khayal.designsystem.theme.FairShareTheme
import com.khayal.onboarding.welcome.Welcome
import com.khayal.test.screenshot.DevicePreviews

private const val LONG_APP_NAME_LABEL = "This is a very long app name that should be wrapped"

private const val LONG_TAGLINE_LABEL = "This is a very very very very very very long tagline......"

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

@PreviewTest
@Preview(name = "Light theme")
@Preview(name = "Large font 2x", fontScale = 2f)
@Composable
fun WelcomeScreenshotLongTextPreview() {
    FairShareTheme {
        Welcome(
            onGetStartedClick = {},
            onLearnMoreClick = {},
            appName = LONG_APP_NAME_LABEL,
            appTagline = LONG_TAGLINE_LABEL
        )
    }
}
