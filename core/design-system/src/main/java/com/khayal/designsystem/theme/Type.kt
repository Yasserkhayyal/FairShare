package com.khayal.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.khayal.designsystem.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontStyles = listOf(FontStyle.Normal, FontStyle.Italic)
val appFontFamily = FontFamily(
    listOf(
        FontWeight.Thin,
        FontWeight.ExtraLight,
        FontWeight.Light,
        FontWeight.Normal,
        FontWeight.Medium,
        FontWeight.SemiBold,
        FontWeight.Bold,
        FontWeight.ExtraBold
    ).flatMap { weight ->
        fontStyles.map { style ->
            Font(
                googleFont = GoogleFont("Inter"),
                fontProvider = provider,
                weight = weight,
                style = style
            )
        }
    }
)

val baseLine = Typography()
@Immutable
data class FairShareTypography(
    val brandAppTitle: TextStyle = mBrandAppTitle,
    val brandTagLine: TextStyle = mBrandTagLine,
    val headingScreenTitle: TextStyle = mHeadingScreenTitle,
    val bodyPrimary: TextStyle = mBodyPrimary,
    val bodySecondary: TextStyle = mBodySecondary,
    val bodyEmphasis: TextStyle = mBodyEmphasis,
    val captionMeta: TextStyle = mCaptionMeta,
    val buttonLabel: TextStyle = mButtonLabel,
)
val LocalFairShareTypography = staticCompositionLocalOf { FairShareTypography() }

val MaterialTheme.fairShareTypography : FairShareTypography
    @Composable
    get() = LocalFairShareTypography.current

private val mBrandAppTitle = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp
)

private val mBrandTagLine = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val mHeadingScreenTitle = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp
)

val mBodyPrimary = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val mBodySecondary = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val mBodyEmphasis = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp
)

val mCaptionMeta = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)

val mButtonLabel = TextStyle(
    fontFamily = appFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp
)
