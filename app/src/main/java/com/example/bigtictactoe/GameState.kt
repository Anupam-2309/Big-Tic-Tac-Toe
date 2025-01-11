package com.example.bigtictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class GameHistory(
    val winner: String,
    val timestamp: Long = System.currentTimeMillis()
)

class GameState {
    var currentPlayer by mutableStateOf("X")
    var activeSmallBoard by mutableStateOf<Int?>(null)
    var bigBoard by mutableStateOf(List(9) { null as String? })
    var smallBoards by mutableStateOf(List(9) { List(9) { null as String? } })
    var xScore by mutableStateOf(0)
    var oScore by mutableStateOf(0)
    var gameHistory by mutableStateOf(listOf<GameHistory>())
    var showInstructions by mutableStateOf(true)
    var gameOver by mutableStateOf(false)
    
    fun resetGame() {
        currentPlayer = "X"
        activeSmallBoard = null
        bigBoard = List(9) { null }
        smallBoards = List(9) { List(9) { null } }
        gameOver = false
    }
    
    fun updateScore(winner: String) {
        if (winner == "X") xScore++ else oScore++
        gameHistory = gameHistory + GameHistory(winner)
    }
} 