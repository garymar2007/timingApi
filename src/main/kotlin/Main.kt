package org.gary

import kotlin.math.tan
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource.Monotonic.markNow

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

    val duration: Duration = timedValue.duration
    println("Duration in inWholeMicroseconds: ${duration.inWholeMicroseconds}")
    println("Duration in inWholeMilliseconds: ${duration.inWholeMilliseconds}")
    println("Duration in inWholeSeconds: ${duration.inWholeSeconds}")
    println("Duration in inWholeMinutes: ${duration.inWholeMinutes}")
    println("Duration in MINUTES: ${duration.toDouble(DurationUnit.MINUTES)}")

    val time = 534_600.seconds
    time.toComponents { days, hours, minutes, seconds, nanoseconds ->
        println("Days: $days, Hours: $hours, Minutes: $minutes, Seconds: $seconds, Nanoseconds: $nanoseconds")
    }

    println(time.toIsoString())
    println(Duration.parseIsoStringOrNull(time.toIsoString()))
}

object SomeLongRunningProcess {
    fun execute() {
        Thread.sleep(1000 * 2)
    }
    fun calculate(): Double {
        return tan(100.00)
    }
}

sealed interface FrameworkPlugin {
    fun beforeOperation()
    fun afteroperation()
}

// Traditional way of implementing stop watch
class StopWatchPlugin : FrameworkPlugin {
    private var startTime: Long = 0
    override fun beforeOperation() {
        startTime = System.nanoTime()
    }

    override fun afteroperation() {
        val endTime = System.nanoTime()
        val duration = (endTime - startTime!!) / 1_000_000
        println("Execution time: $duration milliseconds")
    }
}

// Using ValueTimeMark API
class StopWatchPluginNew(private val timeSource: TimeSource.WithComparableMarks) : FrameworkPlugin {
    private lateinit var startTime: ComparableTimeMark
    private lateinit var endTime: ComparableTimeMark
    var duration: Duration ?= null; private set

    override fun beforeOperation() {
        startTime = timeSource.markNow()
    }

    override fun afteroperation() {
        val endTime = timeSource.markNow()
        duration = endTime - startTime
    }
}