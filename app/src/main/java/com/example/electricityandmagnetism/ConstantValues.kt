package com.example.electricityandmagnetism

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**Color constants*/
const val TopBarBackgroundColor = 0xFF224997
const val TopBarTextColor = 0xFFFFFFFF//0xFF696969
const val textTitleColor = 0xFF000000
const val textSubTitleColor = 0xFF5A5A5A

/**Fundamentals Images Ids*/
const val RDrawableElectricFillerImage = 0
const val RDrawableElectricCharges = 1
const val RDrawableElectricField = 2
const val RDrawableElectrostaticPotentialEnergyIcon = 3

/**Funciones de apoyo más que de despliegue de UI*/
@Composable
fun getDrawableFromFundamentalCard(
    imageid: Int
): Int{
    return when (imageid){
        RDrawableElectricFillerImage -> R.drawable.electric_filler_image
        RDrawableElectricCharges -> R.drawable.electric_charges_icon
        RDrawableElectricField -> R.drawable.electric_field_icon
        RDrawableElectrostaticPotentialEnergyIcon -> R.drawable.electrostatic_potential_energy_icon
        else -> R.drawable.imageplaceholder
    }
}


/*
const val ButtonBackgroundColor = 0xFFFFD966//0xFFFFCA28
const val ClearUserBankColor = 0xC9FF8686
const val BottomBarBackgroundColor = 0xFFFFFAFA
//candidatos = 0xFFFF8686, 0xFFB2FF8F
val AnswerColorBackgrounds = listOf(0xFFFFEBEB, 0xFFECFFE5, 0xFF37FF00,0xFF37FF00,0xFF37FF00,0xFF37FF00 ) //0xFF37FF00Is the default in case the options exceed 2 reactives for now
val asd = Color(0xFF000000)
//0xFFFFEBEB, 0xFFEBFFE2,
const val MAX_INTENTS_TO_REQUEST_DATA = 3
const val GUID = "No sé qué es esto"
*/