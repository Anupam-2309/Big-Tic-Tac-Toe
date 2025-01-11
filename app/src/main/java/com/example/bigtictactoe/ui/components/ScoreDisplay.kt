package com.example.bigtictactoe.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bigtictactoe.ui.theme.OColor
import com.example.bigtictactoe.ui.theme.ScoreTextColor
import com.example.bigtictactoe.ui.theme.Secondary
import com.example.bigtictactoe.ui.theme.XColor

@Composable
fun ScoreDisplay(
    xScore: Int,
    oScore: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Secondary,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreItem("X", xScore, XColor)
            Spacer(modifier = Modifier.width(32.dp))
            ScoreItem("O", oScore, OColor)
        }
    }
}

@Composable
private fun ScoreItem(
    player: String,
    score: Int,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = player,
            style = MaterialTheme.typography.titleLarge,
            color = color
        )
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.headlineMedium,
            color = ScoreTextColor
        )
    }
} 