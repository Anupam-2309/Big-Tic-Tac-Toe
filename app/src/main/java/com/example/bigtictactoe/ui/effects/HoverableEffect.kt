package com.example.bigtictactoe.ui.effects

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.hoverable(
    onEnter: () -> Unit,
    onExit: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    LaunchedEffect(isHovered) {
        if (isHovered) onEnter() else onExit()
    }
    
    return this.hoverable(interactionSource)
} 