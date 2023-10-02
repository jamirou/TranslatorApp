package com.example.translateapp.translator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TranslateView(viewModel: TranslateViewModel) {

    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val languageOptions = viewModel.languageOptions
    val itemsSelection = viewModel.itemSelection
    var indexSource by remember { mutableStateOf(0) }
    var indexTarget by remember { mutableStateOf(1) }
    var expandSource by remember { mutableStateOf(false) }
    var expandTarget by remember { mutableStateOf(false) }

    var selectedSourceLang by remember { mutableStateOf(languageOptions[0]) }
    var selectedTargetLang by remember { mutableStateOf(languageOptions[1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            DropDownLang(
                itemSelection = itemsSelection,
                selectedIndex = indexSource,
                expand = expandSource,
                onClickExpanded = { expandSource = true },
                onClickDismiss = { expandSource = false },
                onClickItem = { index ->
                    indexSource = index
                    selectedSourceLang = languageOptions[index]
                    expandSource = false
                }
            )

            Icon(
                Icons.Default.ArrowForward, contentDescription = "",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )

            DropDownLang(
                itemSelection = itemsSelection,
                selectedIndex = indexTarget,
                expand = expandTarget,
                onClickExpanded = { expandTarget = true },
                onClickDismiss = { expandTarget = false },
                onClickItem = { index ->
                    indexTarget = index
                    selectedTargetLang = languageOptions[index]
                    expandTarget = false
                }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = state.textToTranslate,
            onValueChange = { viewModel.onValue(it) },
            label = { Text(text = "Write...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.onTranslate(
                        state.textToTranslate,
                        context,
                        selectedSourceLang,
                        selectedTargetLang
                    )
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (state.isDownloading){
            CircularProgressIndicator()
            Text(text = "Downloading model")
        }else{
            OutlinedTextField(
                value = state.translateText,
                onValueChange = {},
                label = { Text(text = "") },
                readOnly = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )
        }

    }

}