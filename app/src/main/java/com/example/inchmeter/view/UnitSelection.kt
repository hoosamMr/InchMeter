package com.example.inchmeter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.inchmeter.R
import com.example.inchmeter.model.UnitValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitSelection(
    unitList: List<UnitValue>,
    selectedInput: UnitValue,
    selectedOutput: UnitValue,
    onInputUnitSelected: (UnitValue) -> Unit,
    onOutputUnitSelected: (UnitValue) -> Unit,
    onSwapUnits: () -> Unit
) {
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = inputExpanded,
            onExpandedChange = { inputExpanded = !inputExpanded },
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = selectedInput.name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = inputExpanded)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                unitList.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(text = unit.name) },
                        onClick = {
                            onInputUnitSelected(unit)
                            inputExpanded = false
                        }
                    )
                }
            }
        }

        IconButton(onClick = onSwapUnits) {
            Image(
                painter = painterResource(id = R.drawable.swap_horiz_24px),
                contentDescription = "Swap Units"
            )
        }

        ExposedDropdownMenuBox(
            expanded = outputExpanded,
            onExpandedChange = { outputExpanded = !outputExpanded },
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = selectedOutput.name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = outputExpanded)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                unitList.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(text = unit.name) },
                        onClick = {
                            onOutputUnitSelected(unit)
                            outputExpanded = false
                        }
                    )
                }
            }
        }
    }
}
