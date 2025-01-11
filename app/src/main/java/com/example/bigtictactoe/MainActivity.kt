package com.example.bigtictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.bigtictactoe.ui.screens.GameScreen
import com.example.bigtictactoe.ui.theme.BigTicTacToeTheme
import com.example.bigtictactoe.game.GameState
import com.example.bigtictactoe.ui.animation.WinnerCelebration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BigTicTacToeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val gameState = remember { GameState() }

    GameScreen(
        bigBoard = gameState.bigBoard,
        smallBoards = gameState.smallBoards,
        xScore = gameState.xScore,
        oScore = gameState.oScore,
        currentPlayer = gameState.gameStatus,
        activeSmallBoard = gameState.activeSmallBoard,
        onCellClick = { smallBoardIndex, cellIndex -> 
            gameState.makeMove(smallBoardIndex, cellIndex as Int)
        },
        onResetClick = { gameState.resetGame() },
        showWinnerCelebration = gameState.showWinnerCelebration,
        onWinnerDismiss = { gameState.dismissWinnerCelebration() }
    )
}