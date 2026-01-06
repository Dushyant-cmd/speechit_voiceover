package com.bytezaptech.texttospeech.presentation.introduction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bytezaptech.texttospeech.R
import com.bytezaptech.texttospeech.presentation.custom.HighlightTextCharacters
import com.bytezaptech.texttospeech.presentation.custom.RippleEffectImage
import com.bytezaptech.texttospeech.utils.speakRobotically

@Composable
fun UsecaseScreen(modifier: Modifier = Modifier, fromUsecaseToUsage: (useCase: String) -> Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        var highlightRange by remember { mutableStateOf<IntRange?>(null) }
        val speakText =
            stringResource(R.string.how_you_will_use_speechit)

        RippleEffectImage(R.drawable.baseline_mic_24)

        Spacer(modifier = Modifier.height(20.dp))

        HighlightTextCharacters(text = speakText, highlightRange)

        DisposableEffect(key1 = true) {
            val tts = speakRobotically(context, speakText, 1f, {
                highlightRange = it
            }) {
                fromUsecaseToUsage("Daily")
            }
            onDispose {
                tts.stop()
                tts.shutdown()
            }
        }
    }
}