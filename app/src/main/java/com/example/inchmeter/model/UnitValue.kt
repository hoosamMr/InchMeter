package com.example.inchmeter.model

import kotlin.math.roundToInt

open class UnitValue(val name :String, val measValue : Double ){
    open fun convertTo(
        num: Double,
        otherUnit: UnitValue
    ): Double {
        return ((num * this.measValue / otherUnit.measValue) * 100).roundToInt() / 100.0
    }

}



