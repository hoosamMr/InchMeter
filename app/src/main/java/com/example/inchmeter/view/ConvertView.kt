package com.example.inchmeter.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inchmeter.viewmodel.MeasureConvertViewModel

@Composable
fun ConvertView(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val measureViewModel: MeasureConvertViewModel = hiltViewModel()

    val measure by measureViewModel.toleranceState.collectAsState()
    val convertedMeasure = measureViewModel.getConvertedTolerance()

    val inputUnit by measureViewModel.selectedInputUnit.collectAsState()
    val outputUnit by measureViewModel.selectedOutputUnit.collectAsState()
    val unitList = measureViewModel.unitList

    var value by remember { mutableDoubleStateOf(0.0) }
    var plus by remember { mutableDoubleStateOf(0.0) }
    var minus by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = modifier
            .fillMaxSize().clickable{focusManager.clearFocus()}
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MeasureInputCompose("Value") {
            value = it
            measureViewModel.updateInputValue(value, plus, minus)
        }

        MeasureInputCompose("Plus Tolerance") {
            plus = it
            measureViewModel.updateInputValue(value, plus, minus)
        }

        MeasureInputCompose("Minus Tolerance") {
            minus = it
            measureViewModel.updateInputValue(value, plus, minus)
        }

        UnitSelection(
            unitList = unitList,
            selectedInput = inputUnit,
            selectedOutput = outputUnit,
            onInputUnitSelected = { measureViewModel.changeInputUnit(it) },
            onOutputUnitSelected = { measureViewModel.changeOutputUnit(it) },
            onSwapUnits = { measureViewModel.swapUnits() }
        )


        if (measure != null && convertedMeasure != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black)
                    .padding(16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MeasureCompose(
                    tolerance = measure!!,
                    modifier = Modifier.weight(1f)
                )
                MeasureCompose(
                    tolerance = convertedMeasure,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
