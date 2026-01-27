package com.khayal.onboarding

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
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.google.testing.junit.testparameterinjector.TestParameters
import com.google.testing.junit.testparameterinjector.TestParametersValuesProvider
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

@RunWith(TestParameterInjector::class)
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
    @TestParameters(valuesProvider = WelcomeButtonProvider::class)
    fun verifyButtonContentAndAppearance(
        testTag: String,
        buttonTextResId: Int,
        buttonRole: ButtonRole,
        buttonVariant: ButtonVariant
    ) {
        //Given
        lateinit var context: Context
        composeTestRole.setContent {
            context = LocalContext.current
            Welcome(
                onGetStartedClick = onGetStartedMock,
                onLearnMoreClick = onLearnMoreClickMock
            )
        }
        // Confirm the button displays the correct text from string resources and has the specified role and variant.
        composeTestRole.onNodeWithTag(testTag)
            .assert(
                hasText(context.getString(buttonTextResId)) and
                        SemanticsMatcher.expectValue(ButtonRoleKey, buttonRole)
                        and SemanticsMatcher.expectValue(ButtonVariantKey, buttonVariant)
            )

    }

class WelcomeButtonProvider : TestParametersValuesProvider() {
    override fun provideValues(context: TestParametersValuesProvider.Context?): List<TestParameters.TestParametersValues?> {
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