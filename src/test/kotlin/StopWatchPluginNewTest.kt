import org.gary.StopWatchPluginNew
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TestTimeSource

class StopWatchPluginNewTest{
    @Test
    fun `properly calculates the duration`(){
        val timeSource = TestTimeSource()
        val plugin = StopWatchPluginNew(timeSource)

        plugin.beforeOperation()
        timeSource +=917.milliseconds
        plugin.afteroperation()

        assertEquals(917.milliseconds, plugin.duration)
    }
}