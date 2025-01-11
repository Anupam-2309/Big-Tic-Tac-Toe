package com.example.bigtictactoe

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class SoundManager(context: Context) {
    private var moveSound: MediaPlayer
    private var winSound: MediaPlayer
    private var drawSound: MediaPlayer

    init {
        // Temporarily comment out until sound files are added
        moveSound = MediaPlayer()  // MediaPlayer.create(context, R.raw.move)
        winSound = MediaPlayer()   // MediaPlayer.create(context, R.raw.win)
        drawSound = MediaPlayer()  // MediaPlayer.create(context, R.raw.draw)
    }

    fun playMoveSound() {
        moveSound.start()
    }

    fun playWinSound() {
        winSound.start()
    }

    fun playDrawSound() {
        drawSound.start()
    }

    fun release() {
        moveSound.release()
        winSound.release()
        drawSound.release()
    }
}

@Composable
fun rememberSoundManager(): SoundManager {
    val context = LocalContext.current
    val soundManager = remember { SoundManager(context) }

    DisposableEffect(Unit) {
        onDispose {
            soundManager.release()
        }
    }

    return soundManager
} 