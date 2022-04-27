import kotlin.concurrent.thread

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println("Program starts: ${Thread.currentThread().name}")
    thread { // Creates a background (worker) thread
        println("Fake work starts: ${Thread.currentThread().name}");
        Thread.sleep(1000); // Pretends doing some work... maybe file upload
        println("Fake work ends: ${Thread.currentThread().name}");
    }
    // Even the last statement in the main function was executed, the application waited for our background thread to execute and complete
    println("Program ends: ${Thread.currentThread().name}")
}