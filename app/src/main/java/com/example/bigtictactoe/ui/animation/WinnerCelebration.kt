package com.example.bigtictactoe.ui.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtictactoe.ui.theme.XColor
import com.example.bigtictactoe.ui.theme.OColor
import kotlin.random.Random

@Composable
fun WinnerCelebration(
    winner: String,
    onDismiss: () -> Unit,
    onPlayAgain: () -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onDismiss()
            },
            title = null,
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Confetti animation
                    ConfettiAnimation(winner)
                    
                    // Winner text
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Congrats!",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 28.sp
                            ),
                            color = if (winner == "X") XColor else OColor
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = " $winner",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 48.sp
                            ),
                            color = if (winner == "X") XColor else OColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "\uD83C\uDF8A",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onPlayAgain()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (winner == "X") XColor else OColor
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(
                        "Play Again",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        )
    }
}

@Composable
private fun ConfettiAnimation(winner: String) {
    val particles = remember { List(50) { Random.nextFloat() } }
    val animatedProgress = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000)
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        particles.forEach { seed ->
            val radius = size.minDimension * 0.02f
            val angle = 360f * seed
            val distance = size.minDimension * 0.5f * animatedProgress.value
            
            rotate(angle + 360f * animatedProgress.value) {
                drawCircle(
                    color = when {
                        winner == "X" -> XColor
                        winner == "O" -> OColor
                        else -> Color.White
                    }.copy(alpha = 1f - animatedProgress.value),
                    radius = radius * (1f - animatedProgress.value),
                    center = center + Offset(
                        x = distance * kotlin.math.cos(angle),
                        y = distance * kotlin.math.sin(angle)
                    )
                )
            }
        }
    }
} 