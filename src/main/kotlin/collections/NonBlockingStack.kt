package collections

import java.util.concurrent.atomic.AtomicReference

class NonBlockingStack<T> {
    private val head: AtomicReference<Node<T>> = AtomicReference()

    fun push(data: T) {
        val newHead: Node<T> = Node(data)
        var currentHead: Node<T>?
        do {
            currentHead = head.get()
            newHead.next = currentHead
        } while (!head.compareAndSet(currentHead, newHead))
    }

    fun pop(): T? {
        var currentHead: Node<T>?
        var newHead: Node<T>?
        do {
            currentHead = head.get()
            newHead = currentHead.next
        } while (!head.compareAndSet(currentHead, newHead))
        return currentHead?.data
    }

    fun peek(): T {
        val node: Node<T> = head.get()
        return node.data
    }

    fun isEmpty(): Boolean = head.get() == null

    class Node<T>(val data: T) {
        var next: Node<T>? = null
    }
}
