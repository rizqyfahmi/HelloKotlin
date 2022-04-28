import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun main(args: Array<String>) = runBlocking{// creates a blocking corotine that executes in current thread (Main Thread)
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}") // Main Thread

    val time = measureTimeMillis {
        val messageOne = getMessageOne();
        val messageTwo = getMessageTwo(); // It will be executed after getMessageOne()
        println("The entire message is: $messageOne $messageTwo"); // It will be executed after getMessageTwo()
    }

    println("Completed in $time ms");
    println("Program ends: ${Thread.currentThread().name}") // Main Thread

}

suspend fun getMessageOne(): String {
    delay(1000); // pretends to do some work
    return "Hello";
}

suspend fun getMessageTwo(): String {
    delay(1000); // pretends to do some work
    return "World";
}