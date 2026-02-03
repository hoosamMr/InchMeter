package com.example.inchmeter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inchmeter.model.Tolerance
import java.math.BigDecimal
import kotlin.math.roundToInt

@Composable
fun MeasureCompose(
    tolerance: Tolerance,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = tolerance.name)
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = tolerance.meas.cleanString(), fontSize
            = 36
                .sp)
            Text(" ± ")
            Column {
                Text(text = tolerance.measMax.cleanString())
                Text(text = tolerance.measMin.cleanString())
            }

        }
        Text("Range: ${ (tolerance.meas - tolerance.measMin).cleanString() } ~ ${ (tolerance.meas + tolerance.measMax).cleanString() }")

    }
}

fun Double.cleanString(): String {
    return BigDecimal.valueOf(this)
        .stripTrailingZeros()
        .toPlainString()
}
/*
fun myPip(rng : Double) : Int{
 //my code
   var pip = 3
    var valHolder = (rng * 1000).roundToInt()
    while (valHolder % 10 == 0 && pip > 0){
        valHolder /= 10
        pip--

    }
    return pip
}
 */

/*


fun myPip(rng: Double): Int {
    val bd = BigDecimal.valueOf(rng).stripTrailingZeros()
    return maxOf(0, bd.scale()) // scale() = number of digits after the decimal
}

*/
