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
        try {
            for (i in 0..500) {
                println("number $i")
                /*
                * - Actually, cancellable suspending functions that belong to "Kotlinx.coroutines"
                *   such as yield(), delay(), etc. they throw a CancellationException
                *   on the coroutine cancellation
                * */
                delay(5)
            }
        } catch (ex: CancellationException) {
            println("Exception caught safely")
        } finally { // just like any other try-catch, we can also add "finally" block
            /*
            * - We cannot execute a suspending function from finally block because
            *   the coroutine that runs in this block is already cancelled.
            *   Executing a suspending function from finally block will throw
            *   another CancellationException that makes the next statement in this finally block
            *   not be executed. But in the rear case, if we really need to execute a suspending function
            *   from finally block, then wrap all the code inside finally block within
            *   "withContext(NonCancellable)" function.
            *   ```
            *   // Before:
            *   delay(1000) // it makes the next statement not be executed
            *   println("Close resources in finally")
            *   ```
            * */
            // After
            withContext(NonCancellable) {
                delay(1000)
                println("Close resources in finally")
            }

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