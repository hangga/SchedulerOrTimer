import kotlinx.coroutines.*
import kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * GPT : https://chat.openai.com/share/31425629-841f-4c8b-9ddd-a7427176c750
 */

@InternalCoroutinesApi
/*fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")
    withDelay()
    withRepeat()
    withScheduledExecutorService()
    withCoroutinesScheduling()
}*/

fun withDelay() = runBlocking {
    launch {
        // Eksekusi setelah 1 detik
        println("withDelay() start at " + System.currentTimeMillis())
        delay(1000)
        println("withDelay() stop at " + System.currentTimeMillis())
    }

    // Tetap menjalankan coroutine utama
    delay(2000)
}

fun withRepeat() = runBlocking {
    launch {
        repeat(5) {
            // Eksekusi setiap 1 detik
            delay(1000)
            println("withRepeat() $it")
        }
    }

    // Tetap menjalankan coroutine utama
    delay(6000)
}

fun withScheduledExecutorService() = runBlocking {
    val scheduler = Executors.newScheduledThreadPool(1)

    scheduler.scheduleAtFixedRate({
        println("withScheduledExecutorService() at ${System.currentTimeMillis()}")
    }, 0, 1, TimeUnit.SECONDS)

    // Wait 5 seconds
    delay(5000)

    // Memberhentikan scheduler
    scheduler.shutdown()
}

@InternalCoroutinesApi
fun withCoroutinesScheduling() = runBlocking {
    // Membuat coroutines scheduler
    val scheduler = ExperimentalCoroutineDispatcher(1)

    // Menjadwalkan tugas setiap 1 detik
    val job = launch(scheduler) {
        repeat(5) {
            println("withCoroutinesScheduling() $it executed at ${System.currentTimeMillis()}")
            delay(1000)
        }
    }

    // Menunggu selama 6 detik
    delay(6000)

    // Membatalkan tugas
    job.cancelAndJoin()

    // Memberhentikan scheduler
    scheduler.close()
}