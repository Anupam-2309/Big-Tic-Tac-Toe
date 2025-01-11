package com.example.bigtictactoe.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.components.BigBoard
import com.example.bigtictactoe.ui.components.ScoreDisplay
import com.example.bigtictactoe.ui.theme.Secondary
import com.example.bigtictactoe.ui.animation.WinnerCelebration

@Composable
fun GameScreen(
    bigBoard: List<String?>,
    smallBoards: List<List<String?>>,
    xScore: Int,
    oScore: Int,
    currentPlayer: String,
    activeSmallBoard: Int?,
    onCellClick: (Int, Int) -> Unit,
    onResetClick: () -> Unit,
    showWinnerCelebration: Boolean,
    onWinnerDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section with scores
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            ScoreDisplay(
                xScore = xScore,
                oScore = oScore,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = currentPlayer,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Center section with game board
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BigBoard(
                bigBoard = bigBoard,
                smallBoards = smallBoards,
                activeSmallBoard = activeSmallBoard,
                onCellClick = onCellClick,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(0.8f)
            )
        }

        // Bottom section with reset button
        Button(
            onClick = onResetClick,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary
            )
        ) {
            Text(
                text = "Reset Game",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (showWinnerCelebration) {
            WinnerCelebration(
                winner = currentPlayer,
                onDismiss = onWinnerDismiss,
                onPlayAgain = onResetClick
            )
        }
    }
} 