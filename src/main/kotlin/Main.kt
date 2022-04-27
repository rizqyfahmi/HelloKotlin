import kotlinx.coroutines.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}")
    GlobalScope.launch { // Creates a background coroutine that run on a background
        println("Fake work starts: ${Thread.currentThread().name}");
        mySuspendFun(1000); // Pretends doing some work... maybe file upload (Coroutine is suspended but the thread is free)
        println("Fake work ends: ${Thread.currentThread().name}");
    }
    // it's a coroutine builder that block the current thread (Where it runs in the main thread then it will block the main thread)
    runBlocking {
        mySuspendFun(2000) // wait for coroutine to finish (practically not a right way to wait)
    }
    // This statement will never be executed until runBlocking is finished
    println("Program ends: ${Thread.currentThread().name}")
}

suspend fun mySuspendFun(time: Long) {
    delay(time)
}