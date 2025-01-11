package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.theme.Surface
import com.example.bigtictactoe.ui.theme.XColor
import com.example.bigtictactoe.ui.theme.OColor

@Composable
fun StyledScoreBoard(
    xScore: Int,
    oScore: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Score",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PlayerScore("X", xScore, XColor)
                PlayerScore("O", oScore, OColor)
            }
        }
    }
}

@Composable
private fun PlayerScore(
    player: String,
    score: Int,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Player $player",
            style = MaterialTheme.typography.titleMedium,
            color = color
        )
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.headlineMedium,
            color = color
        )
    }
} 