package com.example.bigtictactoe.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

@Composable
fun HoverTile(
    isHovered: Boolean,
    content: @Composable () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val elevation by animateFloatAsState(
        targetValue = if (isHovered) 16f else 4f,
        animationSpec = tween(200)
    )

    Box(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationX = if (isHovered) 5f else 0f
                rotationY = if (isHovered) 5f else 0f
                shadowElevation = elevation
            }
            .zIndex(if (isHovered) 1f else 0f)
    ) {
        content()
    }
} 