package co.vijay.class9thscience.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import co.vijay.class9thscience.R

val appleLikeFontFamily = FontFamily(
    Font(R.font.poppins, FontWeight.Normal), // Regular
    Font(R.font.poppins_light, FontWeight.Medium),  // Medium
    Font(R.font.poppins_mid, FontWeight.SemiBold), // SemiBold
    Font(R.font.poppins_semibold, FontWeight.Bold)  // Bold
)

val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        color = Color.Black
    ),
    displayMedium = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    displaySmall = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineLarge = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineSmall = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    bodyLarge = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    bodyMedium = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = Color.Black
    ),
    bodySmall = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        color = Color.Black
    ),
    labelLarge = TextStyle(
        fontFamily = appleLikeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = Color.Black
    )

)