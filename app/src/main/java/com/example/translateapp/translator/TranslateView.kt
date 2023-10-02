package com.example.translateapp.translator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TranslateView(viewModel: TranslateViewModel) {
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val languageOptions = viewModel.languageOptions
    val itemsSelection = viewModel.itemSelection
    var indexSource by remember { mutableIntStateOf(0) }
    var indexTarget by remember { mutableIntStateOf(1) }
    var expandedSource by remember { mutableStateOf(false) }
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
                expand = expandedSource,
                onClickExpanded = { expandedSource = true },
                onClickDismiss = { expandedSource = false },
                onClickItem = { index ->
                    indexSource = index
                    selectedSourceLang = languageOptions[index]
                    expandedSource = false
                }
            )
            Icon(
                Icons.Default.ArrowForward, contentDescription = "Translate",
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
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

    }
}