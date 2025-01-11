package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BigBoard(
    bigBoard: List<String?>,
    smallBoards: List<List<String?>>,
    activeSmallBoard: Int?,
    onCellClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (row in 0..2) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (col in 0..2) {
                    val boardIndex = row * 3 + col
                    SmallBoard(
                        board = smallBoards[boardIndex],
                        isActive = activeSmallBoard == null || activeSmallBoard == boardIndex,
                        isWon = bigBoard[boardIndex] != null,
                        winner = bigBoard[boardIndex],
                        onCellClick = { cellIndex -> onCellClick(boardIndex, cellIndex) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
} 