package com.example.translateapp.languages

import androidx.compose.runtime.Composable

@Composable
fun getStrings():List<Map<String, String>> {
    val en = mapOf(
        "title" to "Hello world!",
        "subtitle" to "The world is yours"
    )
    val es = mapOf(
        "title" to "Hola mundo!",
        "subtitle" to "El mundo es tuyo!"
    )
    return listOf(
        en,
        es
    )
}