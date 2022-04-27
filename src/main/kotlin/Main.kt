import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking{
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}")
    val job: Job = launch { // Creates a coroutine in the same thread with the underlying thread of "runBlocking" (it inherits the thread and coroutine scope of the immediate parent coroutine)
        println("Fake work starts: ${Thread.currentThread().name}");
        mySuspendFun(1000); // Pretends doing some work... maybe file upload (Coroutine is suspended but the thread is free)
        println("Fake work ends: ${Thread.currentThread().name}");
    }

    // Wait for "launch" to finish its execution
    // after which the next statement will be executed
    job.join();

    println("Program ends: ${Thread.currentThread().name}")



}

suspend fun mySuspendFun(time: Long) {
    delay(time)
}