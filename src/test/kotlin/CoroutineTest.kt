@file:OptIn(InternalCoroutinesApi::class, InternalCoroutinesApi::class)

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class CoroutineTest {

    private val originalOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @Test
    fun `test withDelay`() {
        System.setOut(PrintStream(outputStreamCaptor))
        runBlocking {
            withTimeout(3000) {
                withDelay()
            }
        }
        System.setOut(originalOut)

        val output = outputStreamCaptor.toString().trim().split('\n')
        assertEquals(2, output.size)
        assertEquals("withDelay() start at", output[0].substring(0, 20))
        assertEquals("withDelay() stop at", output[1].substring(0, 19))
    }

    @Test
    fun `test withRepeat`() {
        System.setOut(PrintStream(outputStreamCaptor))
        runBlocking {
            withTimeout(8000) {
                withRepeat()
            }
        }
        System.setOut(originalOut)

        val output = outputStreamCaptor.toString().trim().split('\n')
        assertEquals(5, output.size)
        assertEquals("withRepeat() 0", output[0])
        assertEquals("withRepeat() 4", output[4])
    }

    @Test
    fun `test withScheduledExecutorService`() {
        System.setOut(PrintStream(outputStreamCaptor))
        runBlocking {
            withTimeout(5000) {
                withScheduledExecutorService()
            }
        }
        System.setOut(originalOut)

        val output = outputStreamCaptor.toString().trim().split('\n')
        assertEquals(6, output.size)
        //assertEquals("withScheduledExecutorService() at", output[0].substring(0, 30))
        //assertEquals("withScheduledExecutorService() at", output[4].substring(0, 30))
    }

    @Test
    fun `test withCoroutinesScheduling`() {
        System.setOut(PrintStream(outputStreamCaptor))
        runBlocking {
            withTimeout(5000) {
                withCoroutinesScheduling()
            }
        }
        System.setOut(originalOut)

        val output = outputStreamCaptor.toString().trim().split('\n')
        assertEquals(5, output.size)
        //assertEquals("withCoroutinesScheduling() 0 executed at", output[0].substring(0, 36))
        //assertEquals("withCoroutinesScheduling() 4 executed at", output[4].substring(0, 36))
    }
}
