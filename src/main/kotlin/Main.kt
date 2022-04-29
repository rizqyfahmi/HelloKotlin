import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking{// creates a blocking corotine that executes in current thread (Main Thread)
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}") // Main Thread

    val time = measureTimeMillis {
        // Function getMessageOne() and getMessageTwo() will be executed concurrently. Means getMessageTwo() doesn't need to wait for getMessageOne() is executed
        /*
        * - "async" in this line make the execution of getMessageOne() happens in the background thread
        * - "start = CoroutineStart.LAZY" make this line doesn't get executed when "messageOne" never be called but make the coroutine is suspended
        * */
        val messageOne: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageOne() };
        /*
        * - "async" in this line make the execution of getMessageTwo() happens in the background thread
        * - "start = CoroutineStart.LAZY" make this line doesn't get executed when "messageTwo" never be called but make the coroutine is suspended
        * */
        val messageTwo: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageTwo() };
        println("The entire message is: ${messageOne.await() + " "+ messageTwo.await()}"); // this statement (println) will wait for executing of getMessageOne() and getMessageTwo() are finished because we added "await()" in each message
    }

    println("Completed in $time ms");
    println("Program ends: ${Thread.currentThread().name}") // Main Thread

}

suspend fun getMessageOne(): String {
    delay(1000); // pretends to do some work
    println("After working in getMessageOne()");
    return "Hello";
}

suspend fun getMessageTwo(): String {
    delay(1000); // pretends to do some work
    println("After working in getMessageTwo()");
    return "World";
}