fun main(args: Array<String>) {
    // -128 to 127
    var age: Byte = 30
    println("Byte: $age")
    // -32768 to 32767
    var distance: Short = 2000
    println("Short: $distance")
    // -2147483648(-2^31) to -2147483647((-2^31) - 1)
    var height: Int = 120
    println("Int: $height")
    // -9223372036854775808(-2^63) to -9223372036854775807((-2^63) - 1)
    // add "L" to make a number becomes Long
    var balance: Long = 100000000L
    println("Long: $balance")
}