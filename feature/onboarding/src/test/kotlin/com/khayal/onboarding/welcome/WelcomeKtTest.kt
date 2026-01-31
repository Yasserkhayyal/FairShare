package com.khayal.onboarding.welcome

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.google.testing.junit.testparameterinjector.TestParameters
import com.google.testing.junit.testparameterinjector.TestParametersValuesProvider
import com.khayal.designsystem.testing.ButtonRoleKey
import com.khayal.designsystem.testing.ButtonVariantKey
import com.khayal.designsystem.ui.buttons.ButtonRole
import com.khayal.designsystem.ui.buttons.ButtonVariant
import com.khayal.onboarding.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestParameterInjector

@RunWith(RobolectricTestParameterInjector::class)
class WelcomeKtTest {

    @get:Rule
    val composeTestRole = createComposeRule()
    private val onGetStartedMock: () -> Unit = mockk(relaxed = true)
    private val onLearnMoreClickMock: () -> Unit = mockk(relaxed = true)

    private lateinit var context: Context

    @Before
    fun setup() {
        //Given
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
    }

    @Test
    fun verifyUIElementsDisplayCorrectly() {
        with(composeTestRole) {
            onNodeWithTag(WALLET_ICON_TAG).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.app_name)).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.app_tagline)).assertIsDisplayed()
            onNodeWithTag(GET_STARTED_BUTTON_TAG).assertIsDisplayed()
            onNodeWithTag(LEARN_MORE_BUTTON_TAG).assertIsDisplayed()
        }
    }

    @Test
    fun verifySpacersAreDisplayedCorrectly() {
        with(composeTestRole) {
            //verify 96.dp spacer between wallet icon container and the outer most container
            val walletContainerBounds = onNodeWithTag(WALLET_ICON_CONTAINER).getBoundsInRoot()
            val outerMostContainerBounds =
                onNodeWithTag(WELCOME_SCREEN_OUTER_MOST_CONTAINER).getBoundsInRoot()
            assertEquals(
                96.dp, walletContainerBounds.top
                        - outerMostContainerBounds.top
            )
            //verify 24.dp spacer between app name text and its parent
            val appNameTextBounds = onNodeWithText(context.getString(R.string.app_name))
                .getBoundsInRoot()
            val appNameTextParentBounds = onNodeWithTag(WELCOME_TEXTS_CONTAINER).getBoundsInRoot()
            assertEquals(
                24.dp, appNameTextBounds.top
                        - appNameTextParentBounds.top
            )
            //verify 8.dp spacer between app tagline text and app name text
            val appTagLineBounds = onNodeWithText(context.getString(R.string.app_tagline))
                .getBoundsInRoot()
            assertEquals(
                8.dp, appTagLineBounds.top
                        - appNameTextBounds.bottom
            )
            //verify 12.dp spacer between get started and learn more buttons
            val getStartedButtonBounds = onNodeWithTag(GET_STARTED_BUTTON_TAG).getBoundsInRoot()
            val learnMoreButtonBounds = onNodeWithTag(LEARN_MORE_BUTTON_TAG).getBoundsInRoot()
            val welcomeButtonsContainerBounds =
                onNodeWithTag(WELCOME_BUTTONS_CONTAINER).getBoundsInRoot()
            assertEquals(
                12.dp, learnMoreButtonBounds.top
                        - getStartedButtonBounds.bottom
            )
            assertEquals(
                32.dp, welcomeButtonsContainerBounds.bottom
                        - learnMoreButtonBounds.bottom
            )

        }
    }

    @Test
    fun testGetStartedButtonClick() {
        //When
        composeTestRole.onNodeWithTag(GET_STARTED_BUTTON_TAG).performClick()
        //Then
        verify(exactly = 1) { onGetStartedMock() }
    }

    @Test
    fun testLearnMoreButtonClick() {
        //When
        composeTestRole.onNodeWithTag(LEARN_MORE_BUTTON_TAG).performClick()
        //Then
        verify(exactly = 1) { onLearnMoreClickMock() }
    }

    @Test
    fun testContentDescriptionForAccessibility() {
        // Check that the wallet icon has the correct content description set for accessibility
        // purposes, ensuring screen readers can announce its function.
        composeTestRole.onNodeWithTag(WALLET_ICON_TAG)
            .assertContentDescriptionEquals(context.getString(R.string.cd_wallet))
    }

    @Test
    @TestParameters(valuesProvider = WelcomeButtonProvider::class)
    fun verifyButtonContentAndAppearance(
        testTag: String,
        buttonTextResId: Int,
        buttonRole: ButtonRole,
        buttonVariant: ButtonVariant
    ) {
        // Confirm the button displays the correct text from string resources and has the specified role and variant.
        composeTestRole.onNodeWithTag(testTag)
            .assert(
                hasText(context.getString(buttonTextResId)) and
                        SemanticsMatcher.expectValue(ButtonRoleKey, buttonRole)
                        and SemanticsMatcher.expectValue(ButtonVariantKey, buttonVariant)
            )

    }

    class WelcomeButtonProvider : TestParametersValuesProvider() {
        override fun provideValues(context: Context?): List<TestParameters.TestParametersValues?> {
            return listOf(
                TestParameters.TestParametersValues.Builder()
                    .name("GetStarted")
                    .addParameter("testTag", GET_STARTED_BUTTON_TAG)
                    .addParameter("buttonTextResId", R.string.get_started)
                    .addParameter(
                        "buttonRole",
                        ButtonRole.PRIMARY
                    )
                    .addParameter(
                        "buttonVariant",
                        ButtonVariant.FILLED
                    )
                    .build(),
                TestParameters.TestParametersValues.Builder()
                    .name("LearnMore")
                    .addParameter("testTag", LEARN_MORE_BUTTON_TAG)
                    .addParameter("buttonTextResId", R.string.learn_more)
                    .addParameter(
                        "buttonRole",
                        ButtonRole.SECONDARY
                    )
                    .addParameter(
                        "buttonVariant",
                        ButtonVariant.FILLED
                    )
                    .build()
            )
        }
    }
}