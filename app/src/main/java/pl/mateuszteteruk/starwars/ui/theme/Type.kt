package pl.mateuszteteruk.starwars.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

// Set of Material typography styles to start with
val Typography = Typography(
    // used for small top app bar
    // could be improved to only local invocation
    titleLarge = TextStyle(
        textAlign = TextAlign.Center
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
