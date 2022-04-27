import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun suspendedFunctionTest() = runBlocking {// Generally it used for writing unit test to test suspending function
        val result = myReturnSuspendedFun()
        Assertions.assertEquals("Hello World", result);
    }
}