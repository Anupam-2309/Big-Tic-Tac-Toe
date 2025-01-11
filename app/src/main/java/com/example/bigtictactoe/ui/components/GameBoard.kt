package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameBoard(
    board: List<String?>,
    onCellClick: (Int, Any?) -> Unit,
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
                    val index = row * 3 + col
                    Cell(
                        value = board[index],
                        enabled = board[index] == null,
                        onClick = { onCellClick(index, null) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

fun onCellClick(index: Int) {

}
 