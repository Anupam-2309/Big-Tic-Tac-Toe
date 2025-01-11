package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.effects.glassEffect
import com.example.bigtictactoe.ui.effects.hoverable
import com.example.bigtictactoe.ui.theme.Secondary
import com.example.bigtictactoe.ui.theme.Surface

@Composable
fun GameTile(
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }

    HoverTile(isHovered = isHovered && isActive) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .shadow(
                    elevation = if (isActive) 8.dp else 2.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = Secondary
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Surface)
                .glassEffect(
                    blur = if (isActive) 8f else 4f,
                    alpha = if (isActive) 0.15f else 0.1f
                )
                .aspectRatio(1f)
                .graphicsLayer {
                    scaleX = if (isHovered && isActive) 1.05f else 1f
                    scaleY = if (isHovered && isActive) 1.05f else 1f
                    rotationX = if (isHovered && isActive) 5f else 0f
                    rotationY = if (isHovered && isActive) 5f else 0f
                }
                .hoverable(
                    onEnter = { isHovered = true },
                    onExit = { isHovered = false }
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
} 