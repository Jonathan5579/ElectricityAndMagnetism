package com.example.electricityandmagnetism.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

@Immutable
data class CustomColorsPalette(

    val topBarBackgroundColor: Color = Color.Unspecified,
    val addCardIconColor: Color = Color.Unspecified,

    val fundamentalsTitleBackground: Color = Color.Unspecified,

    val defaultMathBasicsTitleBackground: Color = Color.Unspecified,
    val defaultMathBasicsTitleCardBackground: Color = Color.Unspecified
    /*
    /**AuditConfigurationScreen*/
    val feedBackCardHorizontalOptionBackgroundColor:Color = Color.Unspecified,
    /**QuestionScreen*/
    val cardEvidenceContentNGColor:Color = Color.Unspecified,
    val cardEvidenceContentOKColor:Color = Color.Unspecified,
    val horizontalBreakBackgroundColor: Color = Color.Unspecified,
    val questionCardBackgroundColor: Color = Color.Unspecified,
    /**Forms*/
    val answerColorBackgrounds: List<Color> = listOf<Color>(),
    val defaultAnswerColorBackground: Color = Color(0xFF8683CE),
    /**SendAnswersScreen*/
    val answerOKBackgroundColor: Color = Color.Unspecified,
    val answerNGBackgroundColor: Color = Color.Unspecified
    */
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(

    topBarBackgroundColor = Color(0xFF224997),
    addCardIconColor = Color(0xFF212121),
    defaultMathBasicsTitleBackground = Color(0xFF272727),
    defaultMathBasicsTitleCardBackground = Color(0xFF000000)

    /*
    /**AuditConfigurationScreen*/
    feedBackCardHorizontalOptionBackgroundColor = Color(0xFFF5F5F5),

    /**QuestionScreen*/
    cardEvidenceContentNGColor = Color(0xFFFFECEC),
    cardEvidenceContentOKColor = Color(0xFFF4FFF1),
    horizontalBreakBackgroundColor = Color(0xFFFFFFFF),
    questionCardBackgroundColor = Color(0xFFFFFFFF),
    /**Forms*/
    answerColorBackgrounds = listOf(
        Color(0xCDFFF3F3),
        Color(0xFFF4FFF1),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6)
    ), //0xFF37FF00Is the default in case the options exceed 2 reactives for now
    answerOKBackgroundColor = Color(0xFFF4FFF1),
    answerNGBackgroundColor = Color(0xCDFFF3F3)
    //0xCDFFF3F, 0xFFF4FFF1, 0xFFD6D6D6
    */
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    topBarBackgroundColor = Color(0xFF0E1B4B),
    addCardIconColor = Color(0xFFC9C9C9),
    defaultMathBasicsTitleBackground = Color(0xFF272727),
    defaultMathBasicsTitleCardBackground = Color(0xFF000000)
    /*
    /**AuditConfigurationScreen*/
    feedBackCardHorizontalOptionBackgroundColor = Color(0xFF929292),

    /**QuestionScreen*/
    cardEvidenceContentNGColor = Color(0xFF5C4E4E),
    cardEvidenceContentOKColor = Color(0xFF576154),
    horizontalBreakBackgroundColor = Color(0xFF000000),
    questionCardBackgroundColor = Color(0xFF363636),
    /**Forms*/
    answerColorBackgrounds = listOf(
        Color(0xFF5C4E4E),
        Color(0xFF576154),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6),
        Color(0xFFD6D6D6)
    ), //0xFF37FF00Is the default in case the options exceed 2 reactives for now,
    answerOKBackgroundColor = Color(0xFF576154),
    answerNGBackgroundColor = Color(0xFF5C4E4E)

     */
)