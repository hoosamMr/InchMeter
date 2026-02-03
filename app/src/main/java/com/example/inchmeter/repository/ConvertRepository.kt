package com.example.inchmeter.repository


import com.example.inchmeter.model.Tolerance
import com.example.inchmeter.model.UnitValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject



class ConvertRepository @Inject constructor() {

    private val units = listOf(
        UnitValue("millimeter", 1.0),
        UnitValue("inch", 25.4)
    )



    fun getUnitNames(units: List<UnitValue?>): List<String> {
        return units.mapNotNull { it?.name }
    }

    fun getUnitByName(name: String): UnitValue {
        return units.find { it.name.equals(name, ignoreCase = true) } ?: units.first()
    }

    fun getAllUnits(): List<UnitValue> = units

    fun createTolerance(value: Double, tolerancePlus: Double, toleranceMinus: Double, unit: UnitValue): Tolerance {
        return Tolerance(
            meas = value,
            measMax = tolerancePlus,
            measMin = toleranceMinus,
            unit = unit
        )
    }

    fun convert(tolerance: Tolerance, targetUnit: UnitValue): Tolerance {
        return tolerance.convertTo(targetUnit)
    }
    fun updateValue(tolerance: Tolerance){

    }
    fun swapUnit(){

    }
}
