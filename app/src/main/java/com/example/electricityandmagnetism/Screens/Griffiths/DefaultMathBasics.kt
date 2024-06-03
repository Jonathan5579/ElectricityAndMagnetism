package com.example.electricityandmagnetism.Screens.Griffiths

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.FundamentalCard
import com.example.electricityandmagnetism.RDrawableElectricCharges
import com.example.electricityandmagnetism.Screens.LaTeXView
import com.example.electricityandmagnetism.R

fun buildMathBasicsContent(): MutableList<FundamentalCard> {
    val fundamentalCards: MutableList<FundamentalCard> = mutableListOf()

    /**DOT PRODUCT*/
    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "Dot product",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Textt(text = "given two vectors v amd w")
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(30f)
                                .clip(RoundedCornerShape(10)),
                            painter = painterResource(id = R.drawable.dot_product_1),
                            contentDescription = "layer icon description",
                        )

                        Column(modifier = Modifier.weight(70f)) {
                            LaTeXView(latex = "\\vec{w} = \\begin{bmatrix} 4 \\\\ 1 \\end{bmatrix}, |\\vec{w}| = \\sqrt{5}, \\theta_{\\vec{w}} = 14.06°")
                            LaTeXView(latex = "\\vec{w} = \\begin{bmatrix} 2 \\\\ -1 \\end{bmatrix}, |\\vec{w}| = \\sqrt{5}, \\theta_{\\vec{w}} = -26.5°")
                        }
                    }

                    Textt(text = "By definition, dot product says that the dot product of two vectors is equal to the length of the projection of w on v, multiplied by the length of v")
                    LaTeXView(latex = "\\begin{bmatrix} 4 \\\\ 1 \\end{bmatrix} \\cdot \\begin{bmatrix}2 \\\\ -1\\end{bmatrix} = (Length_{proj\\vec{w}})(Length_{\\vec{v}})")

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(RoundedCornerShape(10)),
                            painter = painterResource(id = R.drawable.dot_product_2),
                            contentDescription = "layer icon description",
                        )
                    }
                }
            }
        )
    )

    /**GENERALIZED DERIVATIVE IN 3D*/
    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "Generalized derivative in 3D: ▽",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Text(text = "For a temperature funciton T(x,y,z)", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
                    LaTeXView(latex = "dT = \\nabla T \\cdot d\\vec{l} = (\\frac{\\partial T}{\\partial x} \\hat{x} + \\frac{\\partial T}{\\partial y} \\hat{y} + \\frac{\\partial T}{\\partial x} \\hat{x} ) \\cdot (dx \\hat{x} + dy \\hat{y} + dz \\hat{z})")
                    Text(text = "The gradient points in the direction of maximum increase of function T, esto viene de dejar dl constante", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
                    LaTeXView(latex = "\\nabla T \\cdot d\\vec{l} = |\\nabla T| |d\\vec{l}| \\cos \\theta")
                    Text(text = "Si dl es consatante el máximo cambio ocurre cuando el cosθ = 0", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(160.dp)
                                .clip(RoundedCornerShape(32.dp)),
                            painter = painterResource(id = R.drawable.gradient_representation),
                            contentDescription = "layer icon description",
                        )
                    }
                }
            }
        )
    )



    /*
    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electric Field ( E )",
            imageId = RDrawableElectricField,
            content = "A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point, \nE ∝ F/q"
        )
    )
    */

    return fundamentalCards
}

@Composable
fun Textt(text: String = ""){
    Text(text = text, color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
}