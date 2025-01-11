package com.example.bigtictactoe.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class GameState {
    // Game state properties
    var bigBoard by mutableStateOf(List(9) { null as String? })
        private set
    
    var smallBoards by mutableStateOf(List(9) { List(9) { null as String? } })
        private set
    
    var currentPlayer by mutableStateOf("X")
        private set
    
    var activeSmallBoard by mutableStateOf<Int?>(null)
        private set
    
    var xScore by mutableStateOf(0)
        private set
    
    var oScore by mutableStateOf(0)
        private set
    
    var gameStatus by mutableStateOf("Player X's Turn")
        private set
    
    var gameOver by mutableStateOf(false)
        private set
    
    var showWinnerCelebration by mutableStateOf(false)
        private set

    // Bot-related properties
    private var _gameMode by mutableStateOf(GameMode.VS_PLAYER)
    val gameMode: GameMode get() = _gameMode
    
    private val botPlayer = BotPlayer()

    enum class GameMode {
        VS_PLAYER,
        VS_BOT
    }

    private val winningCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
        listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
    )

    fun makeMove(smallBoardIndex: Int, cellIndex: Int): Boolean {
        if (!isValidMove(smallBoardIndex, cellIndex)) return false

        // Make player move
        if (!performMove(smallBoardIndex, cellIndex)) return false

        // If playing against bot and game isn't over, make bot move
        if (_gameMode == GameMode.VS_BOT && !gameOver && currentPlayer == "O") {
            val (botBoardIndex, botCellIndex) = botPlayer.makeMove(
                bigBoard,
                smallBoards,
                activeSmallBoard
            )
            performMove(botBoardIndex, botCellIndex)
        }

        return true
    }

    private fun performMove(smallBoardIndex: Int, cellIndex: Int): Boolean {
        val newSmallBoards = smallBoards.toMutableList()
        val newSmallBoard = newSmallBoards[smallBoardIndex].toMutableList()
        newSmallBoard[cellIndex] = currentPlayer
        newSmallBoards[smallBoardIndex] = newSmallBoard
        smallBoards = newSmallBoards

        if (checkWinner(newSmallBoard)) {
            val newBigBoard = bigBoard.toMutableList()
            newBigBoard[smallBoardIndex] = currentPlayer
            bigBoard = newBigBoard

            if (checkWinner(bigBoard)) {
                handleGameWin()
                return true
            }
        } else if (isSmallBoardFull(newSmallBoard)) {
            val newBigBoard = bigBoard.toMutableList()
            newBigBoard[smallBoardIndex] = "D"
            bigBoard = newBigBoard
        }

        updateGameState(cellIndex)
        return true
    }

    private fun isValidMove(smallBoardIndex: Int, cellIndex: Int): Boolean {
        if (gameOver) return false
        if (smallBoards[smallBoardIndex][cellIndex] != null) return false
        if (bigBoard[smallBoardIndex] != null) return false
        return activeSmallBoard == null || activeSmallBoard == smallBoardIndex
    }

    private fun checkWinner(board: List<String?>): Boolean {
        return winningCombinations.any { (a, b, c) ->
            board[a] != null && board[a] != "D" && 
            board[a] == board[b] && board[a] == board[c]
        }
    }

    private fun isSmallBoardFull(board: List<String?>): Boolean {
        return board.none { it == null }
    }

    private fun handleGameWin() {
        gameOver = true
        showWinnerCelebration = true
        when (currentPlayer) {
            "X" -> xScore++
            "O" -> oScore++
        }
        gameStatus = "Player $currentPlayer Wins the Game!"
    }

    private fun updateGameState(nextBoard: Int) {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
        activeSmallBoard = if (bigBoard[nextBoard] == null) nextBoard else null
        
        gameStatus = when {
            gameOver -> "Player $currentPlayer Wins!"
            activeSmallBoard != null -> "Player $currentPlayer: Play in board ${activeSmallBoard!! + 1}"
            else -> "Player $currentPlayer: Free choice of board"
        }
    }

    fun resetGame() {
        bigBoard = List(9) { null }
        smallBoards = List(9) { List(9) { null } }
        currentPlayer = "X"
        activeSmallBoard = null
        gameOver = false
        showWinnerCelebration = false
        gameStatus = "Player X's Turn"
    }

    fun dismissWinnerCelebration() {
        showWinnerCelebration = false
    }

    fun updateGameMode(newMode: GameMode) {
        _gameMode = newMode
        resetGame()
    }
} 