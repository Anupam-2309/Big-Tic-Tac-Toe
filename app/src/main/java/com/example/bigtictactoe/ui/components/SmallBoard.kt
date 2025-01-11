package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.theme.GridLineColor
import com.example.bigtictactoe.ui.theme.Surface

@Composable
fun SmallBoard(
    board: List<String?>,
    isActive: Boolean,
    isWon: Boolean,
    winner: String?,
    onCellClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Surface)
            .border(
                width = 2.dp,
                color = if (isActive) MaterialTheme.colorScheme.primary else GridLineColor,
                shape = RoundedCornerShape(8.dp)
            )
            .alpha(if (isActive) 1f else 0.7f)
            .padding(4.dp)
    ) {
        if (isWon && winner != "D") {
            // Show winner symbol
            Box(modifier = Modifier.fillMaxSize()) {
                when (winner) {
                    "X" -> XSymbol(modifier = Modifier.fillMaxSize())
                    "O" -> OSymbol(modifier = Modifier.fillMaxSize())
                }
            }
        } else {
            // Show small board grid
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (row in 0..2) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        for (col in 0..2) {
                            val index = row * 3 + col
                            Cell(
                                value = board[index],
                                enabled = isActive && !isWon,
                                onClick = { onCellClick(index) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
} 