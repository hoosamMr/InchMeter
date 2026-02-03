package com.example.inchmeter.model


class Tolerance(
    var meas: Double,
    var measMax: Double,
    var measMin: Double,
    name: String,
    measValue: Double
) : UnitValue(name, measValue){

    constructor(meas: Double,measMax : Double,measMin :
    Double,unit : UnitValue) : this(meas,measMax,measMin,unit.name,unit.measValue)
   override fun convertTo(
        num: Double,
        otherUnit: UnitValue
    ): Double = super.convertTo(num, otherUnit)

    fun convertTo(targetUnit: UnitValue): Tolerance {
        return Tolerance(
            meas = this.convertTo(this.meas, targetUnit),
            measMax = this.convertTo(this.measMax, targetUnit),
            measMin = this.convertTo(this.measMin, targetUnit),
            name = targetUnit.name,
            measValue = targetUnit.measValue
        )
    }



}