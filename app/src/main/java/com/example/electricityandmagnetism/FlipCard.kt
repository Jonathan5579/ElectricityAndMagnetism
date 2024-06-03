package com.example.electricityandmagnetism

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

enum class CardFace(
    val angle: Float
) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
){
    //val rotation = cardFace.angle

    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 150,
            easing = LinearEasing,
        )
    )

    Card(
        elevation = 5.dp,
        onClick = { onClick(cardFace) },
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation.value
            },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (rotation.value <= 90f) {
                front()
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            rotationY = 180f
                        },
                ) {
                    back()
                }
            }
        }
    }
}




/**********************************************************************/

enum class CardFace2(
    val angle: Float
) {
    Front(0f) {
        override val next: CardFace2
            get() = Back
    },
    Back(180f) {
        override val next: CardFace2
            get() = Front
    };

    abstract val next: CardFace2
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlipCard2(
    cardFace: CardFace2,
    onClick: (CardFace2) -> Unit,
    modifier: Modifier = Modifier,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
){
    val rotation = cardFace.angle


    Card(
        onClick = { onClick(cardFace) },
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (rotation <= 90f) {
                front()
            } else {
                back()
            }
        }
    }
}


@Composable
fun FlipCardExample(){
    var cardFace by remember { mutableStateOf(CardFace.Front) }
    FlipCard(
        cardFace = cardFace,
        onClick = { cardFace = cardFace.next },
        modifier = Modifier.size(height = 300.dp, width = 250.dp),
        front = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column {
                    Text(
                        text = "Front",
                        style = MaterialTheme.typography.h3,
                    )
                    Text(text = "cardFace.angle:   ${cardFace.angle}" +
                            "\ncardFace.next:   ${cardFace.next}" +
                            "\ncardFace.name:   ${cardFace.name}" +
                            "\ncardFace.ordinal:   ${cardFace.ordinal}"
                    )
                }
            }
        },
        back = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFCACACA)),
                contentAlignment = Alignment.TopStart,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.h3,
                    )
                    Text(text = "cardFace.angle:   ${cardFace.angle}" +
                            "\ncardFace.next:   ${cardFace.next}" +
                            "\ncardFace.name:   ${cardFace.name}" +
                            "\ncardFace.ordinal:   ${cardFace.ordinal}"
                    )
                }
            }
        },
    )
}