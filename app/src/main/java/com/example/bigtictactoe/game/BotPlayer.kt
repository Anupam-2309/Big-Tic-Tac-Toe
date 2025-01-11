package com.example.bigtictactoe.game

class BotPlayer {
    fun makeMove(
        bigBoard: List<String?>,
        smallBoards: List<List<String?>>,
        activeSmallBoard: Int?
    ): Pair<Int, Int> {
        // Get available moves
        val availableMoves = getAvailableMoves(bigBoard, smallBoards, activeSmallBoard)
        
        // First, try to win any small board
        for ((boardIndex, cellIndex) in availableMoves) {
            val boardCopy = smallBoards[boardIndex].toMutableList()
            boardCopy[cellIndex] = "O"
            if (checkWinner(boardCopy)) {
                return Pair(boardIndex, cellIndex)
            }
        }

        // Block opponent's winning move in small boards
        for ((boardIndex, cellIndex) in availableMoves) {
            val boardCopy = smallBoards[boardIndex].toMutableList()
            boardCopy[cellIndex] = "X"
            if (checkWinner(boardCopy)) {
                return Pair(boardIndex, cellIndex)
            }
        }

        // Try to create a fork or block opponent's fork
        for ((boardIndex, cellIndex) in availableMoves) {
            if (createsFork(smallBoards[boardIndex], cellIndex, "O")) {
                return Pair(boardIndex, cellIndex)
            }
        }

        // Take center of available board if possible
        for ((boardIndex, _) in availableMoves.distinctBy { it.first }) {
            if (smallBoards[boardIndex][4] == null && isValidMove(bigBoard, boardIndex, 4, activeSmallBoard)) {
                return Pair(boardIndex, 4)
            }
        }

        // Take corners
        val corners = listOf(0, 2, 6, 8)
        for (corner in corners) {
            val validMoves = availableMoves.filter { (_, cellIndex) -> cellIndex == corner }
            if (validMoves.isNotEmpty()) {
                return validMoves.first()
            }
        }

        // Take any available move
        return availableMoves.random()
    }

    private fun getAvailableMoves(
        bigBoard: List<String?>,
        smallBoards: List<List<String?>>,
        activeSmallBoard: Int?
    ): List<Pair<Int, Int>> {
        val moves = mutableListOf<Pair<Int, Int>>()
        
        for (boardIndex in 0..8) {
            if (activeSmallBoard != null && boardIndex != activeSmallBoard) continue
            if (bigBoard[boardIndex] != null) continue
            
            for (cellIndex in 0..8) {
                if (smallBoards[boardIndex][cellIndex] == null) {
                    moves.add(Pair(boardIndex, cellIndex))
                }
            }
        }
        
        return moves
    }

    private fun checkWinner(board: List<String?>): Boolean {
        val lines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
            listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
        )

        return lines.any { (a, b, c) ->
            board[a] != null && board[a] == board[b] && board[a] == board[c]
        }
    }

    private fun createsFork(
        board: List<String?>,
        cellIndex: Int,
        player: String
    ): Boolean {
        val boardCopy = board.toMutableList()
        boardCopy[cellIndex] = player
        
        val winningLines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        var potentialWins = 0
        for (line in winningLines) {
            if (line.contains(cellIndex)) {
                val others = line.filter { it != cellIndex }
                if (others.all { boardCopy[it] == null }) {
                    potentialWins++
                }
            }
        }

        return potentialWins >= 2
    }

    private fun isValidMove(
        bigBoard: List<String?>,
        boardIndex: Int,
        cellIndex: Int,
        activeSmallBoard: Int?
    ): Boolean {
        return (activeSmallBoard == null || activeSmallBoard == boardIndex) &&
                bigBoard[boardIndex] == null
    }
} 