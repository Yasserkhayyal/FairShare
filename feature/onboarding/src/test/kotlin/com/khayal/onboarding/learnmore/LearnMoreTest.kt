package com.khayal.onboarding.learnmore

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
import com.khayal.designsystem.ui.stepindicator.STEP_INDICATOR_TAG
import com.khayal.onboarding.R
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestParameterInjector

@RunWith(RobolectricTestParameterInjector::class)
class LearnMoreTest {

    @get:Rule
    val composeTestRole = createComposeRule()

    private lateinit var context: Context

    private val onSkipButtonClickedMock: () -> Unit = mockk(relaxed = true)
    private val onNextButtonClickedMock: () -> Unit = mockk(relaxed = true)

    @Before
    fun setup() {
        composeTestRole.setContent {
            context = LocalContext.current
            LearnMore(
                onSkipButtonClicked = onSkipButtonClickedMock,
                onNextButtonClicked = onNextButtonClickedMock
            )
        }
    }

    @Test
    fun verifyUIElementsDisplayCorrectly() {
        with(composeTestRole) {
            onNodeWithTag(LEARN_MORE_ILLUSTRATION_ICON_TAG).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.learn_more_title)).assertIsDisplayed()
            onNodeWithText(context.getString(R.string.learn_more_description)).assertIsDisplayed()
            onNodeWithTag(STEP_INDICATOR_TAG).assertIsDisplayed()
            onNodeWithTag(SKIP_BUTTON_TAG).assertIsDisplayed()
            onNodeWithTag(NEXT_BUTTON_TAG).assertIsDisplayed()
        }
    }

    @Test
    fun verifySpacersAreDisplayedCorrectly() {
        with(composeTestRole) {
            //verify 24.dp spacer between Learn More Title and Illustration Container
            val learnMoreTitleBounds = onNodeWithText(
                context.getString(R.string.learn_more_title)
            ).getBoundsInRoot()
            val illustrationContainerBounds =
                onNodeWithTag(ILLUSTRATION_CONTAINER_TAG).getBoundsInRoot()
            assertEquals(
                24.0.dp, learnMoreTitleBounds.top
                        - illustrationContainerBounds.bottom
            )
            //verify 12.dp spacer between Learn More Description and Learn More Title
            val learnMoreDescriptionBounds = onNodeWithText(
                context.getString(R.string.learn_more_description)
            ).getBoundsInRoot()
            assertEquals(
                12.0.dp, learnMoreDescriptionBounds.top
                        - learnMoreTitleBounds.bottom
            )
        }

    }

    @Test
    fun testSkipButtonClick() {
        composeTestRole.onNodeWithTag(SKIP_BUTTON_TAG).performClick()
        verify(exactly = 1) { onSkipButtonClickedMock() }
    }

    @Test
    fun testNextButtonClick() {
        composeTestRole.onNodeWithTag(NEXT_BUTTON_TAG).performClick()
        verify(exactly = 1) { onNextButtonClickedMock() }
    }

    @Test
    fun testContentDescriptionForAccessibility() {
        composeTestRole.onNodeWithTag(LEARN_MORE_ILLUSTRATION_ICON_TAG)
            .assertContentDescriptionEquals(context.getString(R.string.cd_learn_more_illustration))
    }

    @Test
    @TestParameters(valuesProvider = LearnMoreButtonProvider::class)
    fun verifyButtonContentAndAppearance(
        testTag: String,
        buttonTextResId: Int,
        buttonRole: ButtonRole,
        buttonVariant: ButtonVariant
    ) {
        with(composeTestRole) {
            onNodeWithTag(testTag).assert(
                hasText(context.getString(buttonTextResId)) and SemanticsMatcher.expectValue(
                    ButtonRoleKey,
                    buttonRole
                ) and SemanticsMatcher.expectValue(ButtonVariantKey, buttonVariant)
            )
        }
    }

    class LearnMoreButtonProvider : TestParametersValuesProvider() {
        override fun provideValues(context: Context?): List<TestParameters.TestParametersValues?> {
            return listOf(
                TestParameters.TestParametersValues.Builder().name("Skip")
                    .addParameter("testTag", SKIP_BUTTON_TAG)
                    .addParameter("buttonTextResId", R.string.skip).addParameter(
                        "buttonRole", ButtonRole.NEUTRAL
                    ).addParameter(
                        "buttonVariant", ButtonVariant.TEXT
                    ).build(),
                TestParameters.TestParametersValues.Builder().name("Next")
                    .addParameter("testTag", NEXT_BUTTON_TAG)
                    .addParameter("buttonTextResId", R.string.next).addParameter(
                        "buttonRole", ButtonRole.PRIMARY
                    ).addParameter(
                        "buttonVariant", ButtonVariant.FILLED
                    ).build()
            )
        }
    }

}