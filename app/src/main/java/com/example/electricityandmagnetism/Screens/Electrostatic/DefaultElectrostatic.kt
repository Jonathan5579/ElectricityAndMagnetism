package com.example.electricityandmagnetism.Screens.Electrostatic

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.FundamentalCard
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.RDrawableElectricCharges
import com.example.electricityandmagnetism.Screens.Griffiths.Textt
import com.example.electricityandmagnetism.Screens.LaTeXView


fun buildElectrostaticContent(): MutableList<FundamentalCard> {
    val fundamentalCards: MutableList<FundamentalCard> = mutableListOf()

    /**Electric field*/
    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "Electric field",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Textt(text = "Ye")
                }
            }
        )
    )


    return fundamentalCards
}
