package com.example.bigtictactoe.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin

@Composable
fun CellAnimation(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) + fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}

@Composable
fun BoardAnimation(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandIn(
            expandFrom = Alignment.Center,
            animationSpec = tween(500)
        ) + fadeIn(),
        exit = shrinkOut() + fadeOut()
    ) {
        content()
    }
} 