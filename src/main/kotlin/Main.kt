import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking{
    // This is not the correct way of using "runBlocking".
    // We use it like this way because we want to show how cool are coroutine
    // Generally "runBlocking" used for writing unit test to test suspending function
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}")
    val job: Job = launch(Dispatchers.Default) {
        for (i in 0..500) {
            if (!isActive) { // "isActive" is the boolean flag that represents cancellation status
                // we can also use "break" for breaking the loop
                return@launch // it used for returning into "launch" level
            }
            println("number $i")
            /*
            * - We use Thread.sleep to avoid invoke suspending functions
            *   that belong to "kotlinx.coroutines" package
            */
            Thread.sleep(1000)
        }
    }

    delay(100) // set delay for cancellation
    // job.cancel() // cancel the coroutine after 50 milliseconds
    // job.join() // wait until the coroutine that is created by "launch" is finished or cancelled
    // Instead of use job.cancel() and job.join() separately, we can use job.cancelAndJoin()
    job.cancelAndJoin()

    println("Program ends: ${Thread.currentThread().name}")

}

suspend fun mySuspendFun(time: Long) {
    delay(time)
}

suspend fun myReturnSuspendedFun(): String {
    mySuspendFun(1000)
    return "Hello World";
}