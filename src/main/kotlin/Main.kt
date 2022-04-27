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
    // We set "Unit" because we are not returning anything
    val job: Deferred<String> = async { // Creates a coroutine in the same thread with the underlying thread of "runBlocking" (it inherits the thread and coroutine scope of the immediate parent coroutine)
        println("Fake work starts: ${Thread.currentThread().name}");
        mySuspendFun(1000); // Pretends doing some work... maybe file upload (Coroutine is suspended but the thread is free)
        println("Fake work ends: ${Thread.currentThread().name}");
        "Hello World"// return value
    }

    /*
    * - Wait for "async" to finish its execution
    *   after which the next statement will be executed
    * - Use join() if you don't want to use the result of async,
    *   Otherwise you can use await() (similar to future and promise in other programming language)
    * - Remember that join() and await() are suspended function which only can be called
    *   from other suspend function or coroutine. In this case is "runBlocking"
    * */
    val result: String = job.await()
    println("Program result: $result")
    println("Program ends: ${Thread.currentThread().name}")



}

suspend fun mySuspendFun(time: Long) {
    delay(time)
}

suspend fun myReturnSuspendedFun(): String {
    mySuspendFun(1000)
    return "Hello World";
}