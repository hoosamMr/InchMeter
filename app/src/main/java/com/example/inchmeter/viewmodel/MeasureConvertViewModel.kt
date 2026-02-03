package com.example.inchmeter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inchmeter.model.Tolerance
import com.example.inchmeter.model.UnitValue
import com.example.inchmeter.repository.ConvertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
@HiltViewModel
class MeasureConvertViewModel @Inject constructor(private val repository: ConvertRepository) : ViewModel() {

    private val _toleranceState = MutableStateFlow<Tolerance?>(null)
    val toleranceState: StateFlow<Tolerance?> = _toleranceState

    val unitList: List<UnitValue> = repository.getAllUnits()
   // fun getUnitsName() = repository.getUnitNames(unitList)
    private val _selectedInputUnit = MutableStateFlow(repository.getUnitByName("inch"))
    private val _selectedOutputUnit = MutableStateFlow(repository.getUnitByName("millimeter"))

    val selectedInputUnit: StateFlow<UnitValue> = _selectedInputUnit
    val selectedOutputUnit: StateFlow<UnitValue> = _selectedOutputUnit

    fun updateInputValue(value: Double, plus: Double = 0.0, minus: Double = 0.0) {
        val inputUnit = _selectedInputUnit.value
        val tolerance = repository.createTolerance(value, plus, minus, inputUnit)
        _toleranceState.value = tolerance
    }

    fun changeInputUnit(unit: UnitValue) {
        _selectedInputUnit.value = unit
    }

    fun changeOutputUnit(unit: UnitValue) {
        _selectedOutputUnit.value = unit
    }

    fun getConvertedTolerance(): Tolerance? {
        val tolerance = _toleranceState.value ?: return null
        return repository.convert(tolerance, _selectedOutputUnit.value)
    }
    fun swapUnits() {
        // Get the result of the current conversion.
        val convertedTolerance = getConvertedTolerance()

        // Swap the backing unit StateFlows.
        val currentInputUnit = _selectedInputUnit.value
        _selectedInputUnit.value = _selectedOutputUnit.value
        _selectedOutputUnit.value = currentInputUnit

        // If there was a converted value, set it as the new source value.
        if (convertedTolerance != null) {
            _toleranceState.value = convertedTolerance
        }
    }

}


/*
class MeasureConvertViewModel : ViewModel() {
  private val _measure = mutableListOf("")
   val measureState : State<String> = _measure
}*/