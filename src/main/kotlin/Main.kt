package org.gary

import kotlin.math.tan
import kotlin.time.measureTimedValue

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//fun stopwatch(block: () -> Unit){
//    val startTime = System.nanoTime()
//    block()
//    val endTime = System.nanoTime()
//    val duration = (endTime - startTime) / 1_000_000
//    println("Execution time: $duration milliseconds")
//}

fun main() {
   val timedValue = measureTimedValue(SomeLongRunningProcess::calculate)
    println("Execution time: $timedValue")
    println("Result: ${timedValue.value}")
    println("Duration: ${timedValue.duration}")
}

object SomeLongRunningProcess {
    fun execute() {
        Thread.sleep(1000 * 2)
    }
    fun calculate(): Double {
        return tan(100.00)
    }
}