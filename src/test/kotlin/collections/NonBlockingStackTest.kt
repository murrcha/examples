package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NonBlockingStackTest {
    @Test
    fun whenPushAndPopToAndFromNonBlockingStack() {
        val stack = NonBlockingStack<String>()
        assertTrue(stack.isEmpty())
        stack.push("123")
        assertFalse(stack.isEmpty())
        assertEquals("123", stack.peek())
        stack.push("345")
        assertEquals("345", stack.peek())
        stack.pop()
        assertEquals("123", stack.peek())
        stack.pop()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun whenTwoThreadsUseNonBlockingStack() {
        val stack = NonBlockingStack<String>()
        val threadOne = TestThread(stack)
        val threadTwo = TestThread(stack)
        threadOne.start()
        threadTwo.start()
        threadOne.join()
        threadTwo.join()
        assertTrue(stack.isEmpty())
    }

    class TestThread(private val stack: NonBlockingStack<String>) : Thread() {
        override fun run() {
            repeat(5) {
                stack.push("any")
            }
            repeat(5) {
                stack.pop()
            }
        }
    }
}
