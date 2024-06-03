package com.example.electricityandmagnetism

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FloatingDraggableExperiment(
    appViewModel: AppViewModel
){

    val appState by appViewModel.appState.collectAsState()

    val unfocusedColor = 0xFF485DB1
    val focusedColor = 0xFF2A43A9
    val unfocusedColorvisible = Color(unfocusedColor)
    val focusedColorvisible = Color(focusedColor)

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var background by remember { mutableStateOf(0xFF224996) }


    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .clip(RoundedCornerShape(50))
            .combinedClickable(
                onClick = {
                    Log.d("draggable", "jijiji")
                },
                onLongClick = {
                    if (offsetX > 50 || offsetY > 50) {
                        Log.d("draggable", "x:$offsetX, y:$offsetY")
                    }
                    //haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    //contextMenuPhotoId = photo.id
                },
                onDoubleClick = {
                    Log.d("draggable", "jojojo")
                    Log.d("draggable", "x:$offsetX, y:$offsetY")
                    appViewModel.updateXY(x = offsetX, y = offsetY)
                    background = if (background != focusedColor) focusedColor else unfocusedColor
                }
            )
            .background(Color(background))
            .size(100.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (background != focusedColor) return@detectDragGestures
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            },
        contentAlignment = Alignment.Center
    ){
        Text("${appState.xoffset},${appState.yoffset}", fontSize = 10.sp,modifier = Modifier.align(
            Alignment.Center))
        //AddSectionIcon(appViewModel = appViewModel)
    }
}