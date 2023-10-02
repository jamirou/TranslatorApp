package com.example.translateapp.translator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateViewModel : ViewModel() {
    var state by mutableStateOf(TranslateState())
        private set

    fun onValue(text: String) {
        state = state.copy(textToTranslate = text)
    }

    fun onTranslate(text: String, context: Context, sourceLang: String, targetLang: String) {
        val options = TranslatorOptions
            .Builder()
            .setSourceLanguage(sourceLang)
            .setTargetLanguage(targetLang)
            .build()
        val languageTranslator = Translation
            .getClient(options)
        languageTranslator.translate(text)
            .addOnSuccessListener { translateText ->
                state = state.copy(
                    translateText = translateText
                )
            }.addOnFailureListener {

            }
        
    }
}





