package com.khayal.onboarding

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khayal.designsystem.testing.ButtonRoleKey
import com.khayal.designsystem.testing.ButtonVariantKey
import com.khayal.designsystem.ui.buttons.ButtonRole
import com.khayal.designsystem.ui.buttons.ButtonVariant
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeKtTest {

    @get:Rule
    val composeTestRole = createComposeRule()
    private val onGetStartedMock: () -> Unit = mockk(relaxed = true)
    private val onLearnMoreClickMock: () -> Unit = mockk(relaxed = true)

    @Test
    fun verifyUIElementsDisplayCorrectly() {
        lateinit var context: Context
        //Given
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        composeTestRole.onNodeWithTag(WALLET_ICON_TAG).assertIsDisplayed()
        composeTestRole.onNodeWithText(context.getString(R.string.app_name))
            .assertIsDisplayed()
        composeTestRole.onNodeWithText(context.getString(R.string.app_tagline))
            .assertIsDisplayed()
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG).assertIsDisplayed()
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG).assertIsDisplayed()
    }

    @Test
    fun testGetStartedButtonClick() {
        //Given
        composeTestRole.setContent {
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        //When
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG).performClick()
        //Then
        verify(exactly = 1) { onGetStartedMock() }
    }

    @Test
    fun testLearnMoreButtonClick() {
        //Given
        composeTestRole.setContent {
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        //When
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG).performClick()
        //Then
        verify(exactly = 1) { onLearnMoreClickMock() }
    }

    @Test
    fun verifyGetStartedButtonContentAndAppearance() {
        //Given
        lateinit var context: Context
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        // Confirm the 'Get Started' button displays the correct text from string resources and has the specified PRIMARY role and FILLED variant.
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG)
            .assert(
                hasText(context.getString(R.string.get_started)) and
                        SemanticsMatcher.expectValue(ButtonRoleKey, ButtonRole.PRIMARY)
                        and SemanticsMatcher.expectValue(ButtonVariantKey, ButtonVariant.FILLED)
            )

    }

    @Test
    fun verifyLLearnMoreButtonContentAndAppearance() {
        // Confirm the 'Learn More' button displays the correct text from string resources and has
        // the specified SECONDARY role and FILLED variant.
        //Given
        lateinit var context: Context
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        //Then
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG)
            .assert(
                hasText(context.getString(R.string.learn_more)) and
                        SemanticsMatcher.expectValue(ButtonRoleKey, ButtonRole.SECONDARY)
                        and SemanticsMatcher.expectValue(ButtonVariantKey, ButtonVariant.FILLED)
            )
    }

    @Test
    fun testContentDescriptionForAccessibility() {
        // Check that the wallet icon has the correct content description set for accessibility
        // purposes, ensuring screen readers can announce its function.
        //Given
        lateinit var context: Context
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        //Then
        composeTestRole.onNodeWithTag(WALLET_ICON_TAG)
            .assertContentDescriptionEquals(context.getString(R.string.cd_wallet))

    }

    @Test
    fun testLayoutStructureAndArrangement() {
        // Verify the overall layout structure, including the vertical arrangement of items,
        // horizontal alignment, and the presence and size of Spacers to ensure correct visual
        // spacing.
        //Given
        lateinit var context: Context
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        val rootNode = composeTestRole.onRoot().getBoundsInRoot()
        val walletIcon = composeTestRole.onNodeWithTag(WALLET_ICON_TAG).getBoundsInRoot()
        val appName =
            composeTestRole.onNodeWithText(context.getString(R.string.app_name)).getBoundsInRoot()
        val appTagline = composeTestRole.onNodeWithText(context.getString(R.string.app_tagline))
            .getBoundsInRoot()
        val getStartedButton =
            composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG).getBoundsInRoot()
        val learnMoreButton = composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG).getBoundsInRoot()

        //Then
        // Check horizontal alignment
        Assert.assertTrue(walletIcon.left > rootNode.left && walletIcon.right < rootNode.right)
        Assert.assertTrue(appName.left > rootNode.left && appName.right < rootNode.right)
        Assert.assertTrue(appTagline.left > rootNode.left && appTagline.right < rootNode.right)
        Assert.assertTrue(getStartedButton.left > rootNode.left && getStartedButton.right < rootNode.right)
        Assert.assertTrue(learnMoreButton.left > rootNode.left && learnMoreButton.right < rootNode.right)

        // Check vertical arrangement
        Assert.assertTrue(walletIcon.bottom < appName.top)
        Assert.assertTrue(appName.bottom < appTagline.top)
        Assert.assertTrue(appTagline.bottom < getStartedButton.top)
        Assert.assertTrue(getStartedButton.bottom < learnMoreButton.top)

        // Check spacer sizes
        composeTestRole.onNodeWithTag(SPACER_96_TAG).assertHeightIsEqualTo(96.dp)
        composeTestRole.onNodeWithTag(SPACER_24_TAG).assertHeightIsEqualTo(24.dp)
        composeTestRole.onNodeWithTag(SPACER_8_TAG).assertHeightIsEqualTo(8.dp)
        composeTestRole.onNodeWithTag(SPACER_12_TAG).assertHeightIsEqualTo(12.dp)
        composeTestRole.onNodeWithTag(SPACER_32_TAG).assertHeightIsEqualTo(32.dp)
    }

    @Test
    fun testUIBehaviorOnSmallPhone() {
        lateinit var context: Context
        composeTestRole.setContent {
            Box(modifier = Modifier.size(DpSize(width = 360.dp, height = 480.dp))) {
                context = LocalContext.current
                Welcome(
                    onGetStartedClick = onGetStartedMock,
                    onLearnMoreClick = onLearnMoreClickMock
                )
            }
        }
        assertProperUIScale(context)
    }

    @Test
    fun testUIBehaviorOnSmallTablet() {
        lateinit var context: Context

        composeTestRole.setContent {
            Box(modifier = Modifier.size(DpSize(width = 600.dp, height = 1024.dp))) {
                context = LocalContext.current
                Welcome(
                    onGetStartedClick = onGetStartedMock,
                    onLearnMoreClick = onLearnMoreClickMock
                )
            }
        }
        assertProperUIScale(context)
    }

    @Test
    fun testUIBehaviorOnLandscapeTablet() {
        lateinit var context: Context
        composeTestRole.setContent {
            Box(modifier = Modifier.size(DpSize(width = 1280.dp, height = 800.dp))) {
                context = LocalContext.current
                Welcome(
                    onGetStartedClick = onGetStartedMock,
                    onLearnMoreClick = onLearnMoreClickMock
                )
            }
        }
        assertProperUIScale(context)
    }

    @Test
    fun testUIWithMockupLightThemeColors() {
        // Apply different MaterialTheme colors (light and dark themes) and verify that the
        // background, icon, text, and button colors update accordingly to match the theme.
        data class TestThemeConfig(val colorScheme: ColorScheme)

        lateinit var context: Context
        val theme = TestThemeConfig(
            colorScheme = lightColorScheme(
                primary = Color.Blue,
                onPrimary = Color.White,
                background = Color.Gray,
                onBackground = Color.DarkGray,
                surfaceVariant = Color.Green,
                onSurface = Color.Magenta
            )
        )

        composeTestRole.setContent {
            context = LocalContext.current
            MaterialTheme(colorScheme = theme.colorScheme) {
                Welcome(
                    onGetStartedClick = onGetStartedMock,
                    onLearnMoreClick = onLearnMoreClickMock
                )
            }
        }
        with(composeTestRole.onNodeWithTag(WALLET_ICON_TAG)) {
            onParent().captureToImage().assertContainsColor(theme.colorScheme.primary)
            captureToImage().assertContainsColor(theme.colorScheme.onPrimary)
        }
        composeTestRole.onNodeWithText(context.getString(R.string.app_name))
            .captureToImage().assertContainsColor(theme.colorScheme.onBackground)
        composeTestRole.onNodeWithText(context.getString(R.string.app_tagline))
            .captureToImage().assertContainsColor(theme.colorScheme.onBackground)
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG)
            .captureToImage().assertContainsColor(theme.colorScheme.primary)
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG)
            .captureToImage().assertContainsColor(theme.colorScheme.surfaceVariant)
    }

    @Test
    fun testUIWithMockupDarkThemeColors() {
        // Apply different MaterialTheme colors (light and dark themes) and verify that the
        // background, icon, text, and button colors update accordingly to match the theme.
        data class TestThemeConfig(val colorScheme: ColorScheme)

        lateinit var context: Context
        val theme = TestThemeConfig(
            colorScheme = darkColorScheme(
                primary = Color.Red,
                onPrimary = Color.White,
                background = Color.Yellow,
                onBackground = Color.Gray,
                surfaceVariant = Color.LightGray,
                onSurface = Color.DarkGray
            )
        )

        composeTestRole.setContent {
            context = LocalContext.current
            MaterialTheme(colorScheme = theme.colorScheme) {
                Welcome(
                    onGetStartedClick = onGetStartedMock,
                    onLearnMoreClick = onLearnMoreClickMock
                )
            }
        }
        with(composeTestRole.onNodeWithTag(WALLET_ICON_TAG)) {
            onParent().captureToImage().assertContainsColor(theme.colorScheme.primary)
            captureToImage().assertContainsColor(theme.colorScheme.onPrimary)
        }
        composeTestRole.onNodeWithText(context.getString(R.string.app_name))
            .captureToImage().assertContainsColor(theme.colorScheme.onBackground)
        composeTestRole.onNodeWithText(context.getString(R.string.app_tagline))
            .captureToImage().assertContainsColor(theme.colorScheme.onBackground)
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG)
            .captureToImage().assertContainsColor(theme.colorScheme.primary)
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG)
            .captureToImage().assertContainsColor(theme.colorScheme.surfaceVariant)
    }

    @Test
    fun testUIWithLongTextResources() {
        // Use mock string resources with exceptionally long text for the app name, tagline,
        // and buttons to check for text wrapping, truncation, or layout breaking issues.
        //Given
        val longAppName = "This is a very long app name that should be wrapped"
        val longAppTagline = "This is a very long tagline that should be wrapped"
        composeTestRole.setContent {
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock,
                appName = longAppName,
                appTagline = longAppTagline
            )
        }
        val rootBounds = composeTestRole.onRoot().getBoundsInRoot()
        val appNameNode = composeTestRole.onNodeWithText(longAppName)
        val appNameBounds = appNameNode.getBoundsInRoot()
        val appTaglineNode = composeTestRole.onNodeWithText(longAppTagline)
        val appTaglineBounds = appTaglineNode.getBoundsInRoot()

        //Then
        appNameNode.assertIsDisplayed()
        appTaglineNode.assertIsDisplayed()
        with(appNameBounds) {
            Assert.assertTrue(
                "App Name should be completely within root bounds",
                top >= rootBounds.top && bottom <= rootBounds.bottom
                        && left >= rootBounds.left && right <= rootBounds.right
            )
            Assert.assertTrue(
                "App Name shouldn't overlap with App Tagline",
                bottom <= appTaglineBounds.top
            )
        }
        with(appTaglineBounds) {
            Assert.assertTrue(
                "App Tagline should be completely within root bounds",
                top >= rootBounds.top && bottom <= rootBounds.bottom
                        && left >= rootBounds.left && right <= rootBounds.right
            )
        }
    }

    private fun ImageBitmap.assertContainsColor(expectedColor: Color) {
        val pixelMap = this.toPixelMap()
        for (x in 0 until pixelMap.width) {
            for (y in 0 until pixelMap.height) {
                if (pixelMap[x, y] == expectedColor) return
            }
        }
        throw AssertionError("Color $expectedColor is not found in the provided image")
    }

    private fun assertProperUIScale(context: Context) {
        val rootBounds = composeTestRole.onRoot().getBoundsInRoot()

        // Define nodes to check
        val nodes = listOf(
            composeTestRole.onNodeWithTag(WALLET_ICON_TAG),
            composeTestRole.onNodeWithText(context.getString(R.string.app_name)),
            composeTestRole.onNodeWithText(context.getString(R.string.app_tagline)),
            composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG),
            composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG)
        )

        // 1. Assert that all nodes are displayed and within the root bounds
        nodes.forEach { node ->
            val nodeBounds = node.getBoundsInRoot()
            node.assertIsDisplayed()
            with(nodeBounds) {
                Assert.assertTrue(
                    "Node should be completely within root bounds",
                    top >= rootBounds.top && bottom <= rootBounds.bottom
                            && left >= rootBounds.left && right <= rootBounds.right
                )
            }
        }

        // 2. Assert that key elements do not overlap
        val nodeBoundsList = nodes.map { it.getBoundsInRoot() }
        for (i in nodeBoundsList.indices) {
            for (j in i + 1 until nodeBoundsList.size) {
                Assert.assertFalse(
                    "Elements should not overlap",
                    with(nodeBoundsList[i]) {
                        right > nodeBoundsList[j].left && left < nodeBoundsList[j].right
                                && bottom > nodeBoundsList[j].top
                                && top < nodeBoundsList[j].bottom
                    }
                )
            }
        }
    }
}