package com.bytezaptech.texttospeech.presentation.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytezaptech.texttospeech.R
import com.bytezaptech.texttospeech.presentation.HighlightTextCharacters
import com.bytezaptech.texttospeech.presentation.rippleLoadingAnimationModifier
import com.bytezaptech.texttospeech.presentation.theme.fontFamily
import com.bytezaptech.texttospeech.speakRobotically

@Composable
fun IntroductionScreen(modifier: Modifier = Modifier, fromIntroToSignup: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        var highlightRange by remember { mutableStateOf<IntRange?>(null) }
        val speakText =
            stringResource(R.string.hi_there_spend_a_moment_with_our_app_and_we_ll_tailor_the_experience_just_for_you)

        Column(modifier = Modifier.padding(top = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.baseline_mic_24),
                colorFilter = ColorFilter.tint(color = Color.White),
                contentDescription = "Sample",
                modifier = Modifier
                    .border(1.dp, color = Color.Black, shape = CircleShape)
                    .background(color = Color.Blue, shape = CircleShape)
                    .size(120.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .rippleLoadingAnimationModifier(
                        start = true,
                        color = Color.White,
                        circles = 2,
                        expandFactor = 2f
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            HighlightTextCharacters(text = speakText, highlightRange)

            DisposableEffect(key1 = true) {
                val tts = speakRobotically(context, speakText) {
                    highlightRange = it
                }
                onDispose {
                    tts.stop()
                    tts.shutdown()
                }
            }
        }

        Button(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(8.dp)),
            onClick = {
                fromIntroToSignup()
            }) {
            Text(
                text = "Continue",
                fontFamily = fontFamily,
                fontSize = 18.sp
            )
        }
    }
}