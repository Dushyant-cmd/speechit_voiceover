package com.bytezaptech.texttospeech

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun speakRobotically(
    context: Context,
    textToSpeech: String,
    highlightRange: (IntRange?) -> Unit,
    fromIntroToUsecase: () -> Unit
): TextToSpeech {
    var tts: TextToSpeech? = null

    tts = TextToSpeech(context) {
        if (it == TextToSpeech.SUCCESS) {
            tts?.language = java.util.Locale.UK
            val params = Bundle().apply {
                putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "tts_utterance_id")
            }
            tts?.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, params, "tts_utterance_id")
        }
    }

    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onDone(p0: String?) {
            Log.d("extension.kt", "onDone: ")
            CoroutineScope(context = Dispatchers.Main).launch {
                delay(2000)
                fromIntroToUsecase()
            }
        }

        override fun onError(p0: String?) {
        }

        override fun onRangeStart(
            utteranceId: String?,
            start: Int,
            end: Int,
            frame: Int
        ) {
            highlightRange(start until end)
        }

        override fun onStart(p0: String?) {
            Log.d("extension.kt", "onStart: ")
        }
    })

    return tts
}