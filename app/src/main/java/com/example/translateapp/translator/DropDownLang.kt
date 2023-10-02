package com.example.translateapp.translator

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DropDownLang(
    itemSelection: List<String>,
    selectedIndex: Int,
    expand: Boolean,
    onClickExpanded: () -> Unit,
    onClickDismiss: () -> Unit,
    onClickItem: (index:Int) -> Unit
) {
    Box(modifier = Modifier) {
        OutlinedButton(onClick = { onClickExpanded() }) {
            Text(text = itemSelection[selectedIndex])
            Icon(Icons.Default.ArrowDropDown , contentDescription = "select language" )
        }

        DropdownMenu(expanded = expand, onDismissRequest = { onClickDismiss() }) {
            itemSelection.forEachIndexed {index, item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = { onClickItem(index) })
            }
        }

    }
}