package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.bigtictactoe.ui.theme.OColor
import com.example.bigtictactoe.ui.theme.XColor

@Composable
fun XSymbol(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val strokeWidth = size.width * 0.1f
        
        // Draw first line (\)
        drawLine(
            color = XColor,
            start = center.copy(
                x = center.x - size.width * 0.3f,
                y = center.y - size.height * 0.3f
            ),
            end = center.copy(
                x = center.x + size.width * 0.3f,
                y = center.y + size.height * 0.3f
            ),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        
        // Draw second line (/)
        drawLine(
            color = XColor,
            start = center.copy(
                x = center.x - size.width * 0.3f,
                y = center.y + size.height * 0.3f
            ),
            end = center.copy(
                x = center.x + size.width * 0.3f,
                y = center.y - size.height * 0.3f
            ),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun OSymbol(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val strokeWidth = size.width * 0.1f
        val radius = (size.width.coerceAtMost(size.height) - strokeWidth) * 0.3f
        
        drawCircle(
            color = OColor,
            radius = radius,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )
    }
} 