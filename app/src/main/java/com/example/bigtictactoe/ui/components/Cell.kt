package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.theme.Surface

@Composable
fun Cell(
    value: String?,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(4.dp))
            .background(Surface)
            .clickable(
                enabled = enabled && value == null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        when (value) {
            "X" -> XSymbol(modifier = Modifier.fillMaxSize(0.7f))
            "O" -> OSymbol(modifier = Modifier.fillMaxSize(0.7f))
        }
    }
} 