package com.khayal.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khayal.designsystem.theme.fairShareTypography
import com.khayal.designsystem.ui.buttons.AppButton
import com.khayal.designsystem.ui.buttons.ButtonRole
import com.khayal.designsystem.ui.buttons.ButtonVariant

const val WALLET_ICON_TAG = "WalletIcon"
const val GET_STARTED_BUTTON_TAG = "getStartedButton"
const val LEARN_MORE_BUTTON_TAG = "learnMoreButton"
const val SPACER_96_TAG = "Spacer96"
const val SPACER_24_TAG = "Spacer24"
const val SPACER_8_TAG = "Spacer8"
const val SPACER_12_TAG = "Spacer12"
const val SPACER_32_TAG = "Spacer32"

@Composable
fun Welcome(
    onGetStartedClick: () -> Unit,
    onLearnMoreClick: () -> Unit,
    appName: String = stringResource(R.string.app_name),
    appTagline: String = stringResource(R.string.app_tagline)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier
            .size(96.dp)
            .testTag(SPACER_96_TAG))

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
                    .testTag(WALLET_ICON_TAG),
                imageVector = Icons.Filled.AccountBalanceWallet,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(R.string.cd_wallet)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier
                .size(24.dp)
                .testTag(SPACER_24_TAG))
            Text(
                text = appName,
                style = MaterialTheme.fairShareTypography.brandAppTitle,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier
                .size(8.dp)
                .testTag(SPACER_8_TAG))
            Text(
                text = appTagline,
                style = MaterialTheme.fairShareTypography.brandTagLine,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(GET_STARTED_BUTTON_TAG),
                buttonContent = {
                    Text(
                        text = stringResource(R.string.get_started),
                        style = MaterialTheme.fairShareTypography.buttonLabel
                    )
                },
                onClick = onGetStartedClick,
                buttonRole = ButtonRole.PRIMARY,
                buttonVariant = ButtonVariant.FILLED,
                enabled = true
            )
            Spacer(modifier = Modifier
                .size(12.dp)
                .testTag(SPACER_12_TAG))
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(LEARN_MORE_BUTTON_TAG),
                buttonContent = {
                    Text(
                        text = stringResource(R.string.learn_more),
                        style = MaterialTheme.fairShareTypography.buttonLabel
                    )
                },
                onClick = onLearnMoreClick,
                buttonRole = ButtonRole.SECONDARY,
                buttonVariant = ButtonVariant.FILLED,
                enabled = true
            )
            Spacer(modifier = Modifier
                .size(32.dp)
                .testTag(SPACER_32_TAG))
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    Welcome(
        onGetStartedClick = {},
        onLearnMoreClick = {}
    )
}