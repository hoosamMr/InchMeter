# InchMeter 📏

InchMeter is my first hands-on Android application.
I created this project while learning **Hilt**, and I am continuing to evolve it by improving domain logic and Kotlin language usage.

The app focuses on **measurement units and tolerance conversion**, with an emphasis on understanding how logic should be structured and documented.

---

## 🎯 Learning Goals

This project is used to practice and understand:

- Kotlin **classes and data classes**
- Kotlin **property delegation**
- Unit conversion logic
- Refactoring and improving domain logic
- Writing clear technical documentation

---

## 🧠 Core Domain Classes

The following classes represent the **core domain logic** of the InchMeter app.
They are written in **pure Kotlin** and are independent of Android UI components.

---

## 🔹 UnitValue

`UnitValue` represents a **measurement unit** (for example: millimeter, inch, centimeter).

It stores:
- The unit name
- The unit’s value relative to millimeters

It is also used to:
- Convert numeric values between units
- Act as a delegated property using Kotlin’s `getValue` / `setValue` operators

```kotlin
open class UnitValue(val name : String, var valueInMM : Double){

    open fun convertTo(
        num : Double,
        otherUnit : UnitValue
    ) : Double =
        ((num * this.valueInMM / otherUnit.valueInMM) * 100)
            .roundToInt() / 100.0

    operator fun getValue(
        thisRef : Any?,
        property : KProperty<*>
    ) : UnitValue {
        return this
    }

    operator fun setValue(
        thisRef : Any?,
        property : KProperty<*>,
        value : UnitValue
    ) {
        this.valueInMM = value.valueInMM
    }
}
## 🔹 Tolerance

`Tolerance` is a **core domain data class** that represents a measurement with an allowed range.

It contains:
- The measured value
- The minimum allowed value
- The maximum allowed value
- The unit of measurement

This class is responsible for converting **all related values together** when changing units.

### Tolerance class implementation

```kotlin
data class Tolerance(
    val measure: Double,
    val minMeasure: Double,
    val maxMeasure: Double,
    val unit: UnitValue
) {

    fun convertTo(targetUnit: UnitValue): Tolerance {
        val factor = unit.valueInMM / targetUnit.valueInMM

        return copy(
            measure = measure * factor,
            minMeasure = minMeasure * factor,
            maxMeasure = maxMeasure * factor,
            unit = targetUnit
        )
    }
}
