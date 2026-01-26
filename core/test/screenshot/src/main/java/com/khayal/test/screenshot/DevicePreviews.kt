package com.khayal.test.screenshot

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * A custom multi-preview annotation that groups common screenshot test cases.
 *
 * This includes:
 * - Default light theme
 * - Dark theme
 * - A large font scale to verify accessibility (2.0x is the maximum users can set in system settings)
 */
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Large font", fontScale = 2f)
annotation class DevicePreviews