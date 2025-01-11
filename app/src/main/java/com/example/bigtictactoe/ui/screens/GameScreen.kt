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
import com.example.bigtictactoe.game.GameState

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
    gameMode: GameState.GameMode,
    onGameModeChange: (GameState.GameMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Add game mode selector at the top
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameModeButton(
                text = "VS Player",
                selected = gameMode == GameState.GameMode.VS_PLAYER,
                onClick = { onGameModeChange(GameState.GameMode.VS_PLAYER) }
            )
            GameModeButton(
                text = "VS Bot",
                selected = gameMode == GameState.GameMode.VS_BOT,
                onClick = { onGameModeChange(GameState.GameMode.VS_BOT) }
            )
        }

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

@Composable
private fun GameModeButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary 
                           else MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text)
    }
} 