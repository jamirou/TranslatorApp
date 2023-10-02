package com.example.translateapp.translator

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
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
                Toast.makeText(context, "Downloading model...", Toast.LENGTH_SHORT).show()
                downloadingModel(languageTranslator, context)
            }
    }
    private fun downloadingModel(languageTranslator: Translator, context: Context) {
        state = state.copy(
            isDownloading = true
        )
        val conditions = DownloadConditions
            .Builder()
            .requireWifi()
            .build()
        languageTranslator
            .downloadModelIfNeeded()
            .addOnSuccessListener {
                Toast.makeText(context, "Model downloaded", Toast.LENGTH_SHORT).show()
                state = state.copy(
                    isDownloading = false
                )
            }.addOnFailureListener {
                Toast.makeText(context, "Failed model downloaded", Toast.LENGTH_SHORT).show()
            }
    }
}





