package com.lofinif.gosporttestapp.ui.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lofinif.gosporttestapp.R

private val light = Font(R.font.sf_ui_display_light, FontWeight.W300)
private val regular = Font(R.font.sf_ui_display_regular, FontWeight.W400)
private val medium = Font(R.font.roboto_medium, FontWeight.W500)
private val semibold = Font(R.font.roboto_regular, FontWeight.W600)

private val SfFontFamily = FontFamily(fonts = listOf(light, regular))
private val robotoFontFamily = FontFamily(fonts = listOf(medium, semibold))


val GoToSportTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = SfFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp
    ),
    displayMedium = TextStyle(
        fontFamily = SfFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 60.sp
    ),
    displaySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SfFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SfFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SfFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp
    )
)