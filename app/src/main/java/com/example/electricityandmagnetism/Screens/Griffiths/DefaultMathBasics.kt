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
import androidx.compose.ui.text.font.FontWeight
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
                    Textt(text = "Suppose a temperature function T(x,y,z), we want to generalize the notion of \"derivative\" of functions like T")
                    Textt(text = "Derivative states how fast a function varies, in 3D this  rate depends on the direction matters (if going up straight might change faster but not so much horizontally, hot gases have less density)")
                    Textt(text = "Apparently this indicates on infinite rates of changes that depends on the direction, fortunately a theorem on partial derivatives states that")
                    LaTeXView(latex = "dT = \\frac{\\partial T}{\\partial x} dx + \\frac{\\partial T}{\\partial y} dy + \\frac{\\partial T}{\\partial z} dz")
                    Textt("So to compute the derivative in 3D, only three derivatives are enough, and this resembles a dot product")
                    LaTeXView(latex = "dT = \\nabla T \\cdot d\\vec{l} = (\\frac{\\partial T}{\\partial x} \\hat{x} + \\frac{\\partial T}{\\partial y} \\hat{y} + \\frac{\\partial T}{\\partial x} \\hat{x} ) \\cdot (dx \\hat{x} + dy \\hat{y} + dz \\hat{z})")
                    Text(text = "The geometrical interpretation gradient points in the direction of maximum increase of function T, esto viene de dejar dl constante", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
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

    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "Fundamental theorem for graedients",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Textt(text = "Given a vectorial funciton T(x,y,z), starting at point \"a\" if you displace a distance dl1, the change in temperature is given by")
                    LaTeXView(latex = "dT = (\\nabla T) \\cdot d\\vec{\\textrm{l}_1}")
                    Textt(text = "making another displacement dl2 the diferential will be")
                    LaTeXView(latex = "dT = \\nabla T \\cdot d\\vec{\\textrm{l}_2}")
                    Textt(text = "Repeating this process until point b, the total change in T along the selected path is")
                    LaTeXView(latex = "\\int_a^b \\nabla T \\cdot d\\vec{\\textrm{l}} = T(b) - T(a)")
                    Textt(text = "This states that for the gradient, the line integral in space is path independant")
                }
            }
        )
    )

    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "The fundamental theorem for divergences",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Textt("The fundamental theory for divergences relates a volume integral with a surface integral like")
                    LaTeXView(latex = "\\int_{V}^{} \\nabla \\cdot \\vec{\\textrm{v}} d\\vec{\\tau} = \\oint_S \\vec{\\textrm{v}} \\cdot d\\vec{\\textrm{a}}")
                    Textt("Where: \n- V refers to a volume integral\n- v a vectorial functions\n- dτ volume diferential = dxdydz in cartesian coordinates")
                    Textt("Also known as Divergence Theorem, Gauss Theorem, Greens Theorem")
                }
            }
        )
    )

    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "The fundamental theorem for curls",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column() {
                    Textt("The fundamental theory for curls relates a surface integral with a closed line integral like")
                    LaTeXView(latex = "\\int_{S}^{} (\\nabla \\times \\vec{\\textrm{v}}) \\cdot d\\vec{\\textrm{a}} = \\oint_S \\vec{\\textrm{v}} \\cdot d\\vec{\\textrm{l}}")
                    Textt("Where: \n- S refers to a volume integral\n- v a vectorial functions\n- da and dl the area and path diferentials")
                    Textt("Also known as Stokes Theorem, Gauss Theorem, Greens Theorem")
                }
            }
        )
    )

    fundamentalCards.add(
        FundamentalCard(
            id = fundamentalCards.size,
            title = "Spherical Coordinate system",
            imageId = RDrawableElectricCharges,
            contentComposable = {
                Column {
                    Textt("A vector in spherical coordinates is determined by (r,θ,Φ)")
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(320.dp, 224.dp)
                                .clip(RoundedCornerShape(10)),
                            painter = painterResource(id = R.drawable.spherical_coordinates),
                            contentDescription = "Spherical coordinates description",
                        )
                    }

                    Textt("The infinitesimal displacements dl throughout the 3 compontents are:")
                    //LaTeXView(latex = "\\textrm{The infinitesimal displacements are } d\\textrm{l}_{r} \\textrm{, } d\\textrm{l}_{\\theta} \\textrm{ } d\\textrm{l}_{\\phi}")

                    val multiplespaces = "\\textrm{ } \\textrm{ } \\textrm{ } \\textrm{ }"
                    //LaTeXView(latex = "\\textrm{The infinitesimal displacements are } d\\textrm{l}_{r} \\textrm{, } d\\textrm{l}_{\\theta} \\textrm{ } d\\textrm{l}_{\\phi}")
                    LaTeXView(latex = "d \\textrm{l}_{r} = dr \\textrm{,} $multiplespaces d \\textrm{l}_{\\theta} = r d\\theta $multiplespaces d \\textrm{l}_{\\phi} = r \\sin(\\theta) d\\phi")
                    /*LaTeXView(latex = "d \\textrm{l}_{r} = dr")
                    LaTeXView(latex = "d \\textrm{l}_{\\theta} = r d\\theta ")
                    LaTeXView(latex = "d \\textrm{l}_{\\phi} = r \\sin(\\theta) d\\phi ")*/

                }
            }
        )
    )

    return fundamentalCards
}

@Composable
fun Textt(text: String = ""){
    Text(text = text, color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 16.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraLight)
}