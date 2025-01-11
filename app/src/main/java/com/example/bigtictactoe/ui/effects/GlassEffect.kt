package com.example.bigtictactoe.ui.effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.glassEffect(
    blur: Float = 12f,
    alpha: Float = 0.2f
) = this.then(
    Modifier
        .blur(blur.dp)
        .alpha(alpha)
        .background(
            Color.White.copy(alpha = 0.12f)
        )
)

@Composable
fun GlassBackground(
    modifier: Modifier = Modifier,
    alpha: Float = 0.1f
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Color.White.copy(alpha = alpha)
            )
            .blur(20.dp)
    )
} 