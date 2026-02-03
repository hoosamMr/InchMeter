package com.example.inchmeter.view

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MeasureInputCompose(
    label: String,
    onValueChange: (Double) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            val parsed = it.toDoubleOrNull()
            if (parsed != null) {
                onValueChange(parsed)
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy
            (keyboardType = KeyboardType.Number),
        singleLine = true
    )
}


