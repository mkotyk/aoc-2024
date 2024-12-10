package com.psyndicate.aoc

class LinkedList<T> {
    class Node<T>(val value: T, var next: Node<T>? = null, var previous: Node<T>? = null)
    var first: Node<T>? = null
        private set
    var last: Node<T>? = null
        private set

    fun append(value: T) {
        val node = Node(value, null, last)
        last?.next = node
        last = node
        if (first == null) { first = node }
    }

    fun prepend(value: T) {
        val node = Node(value, first, null)
        first?.previous = node
        first = node
        if (last == null) { last = node }
    }

    fun insertAfter(node: Node<T>, value: T) {
        val newNode = Node(value, node.next, node)
        node.next?.previous = newNode
        node.next = newNode
        if (last == node) { last = newNode }
    }

    fun remove(node: Node<T>) {
        node.previous?.next = node.next
        node.next?.previous = node.previous
        if (first == node) first = node.next
        if (last == node) last = node.previous
    }

    fun iterator(): Iterable<T> = nodeForwardIterator().map { it.value }

    fun nodeForwardIterator(): Iterable<Node<T>> = Iterable {
        iterator {
            var node = first
            while (node != null) {
                yield(node)
                node = node.next
            }
        }
    }

    fun nodeReverseIterator(): Iterable<Node<T>> = Iterable {
        iterator {
            var node = last
            while (node != null) {
                yield(node)
                node = node.previous
            }
        }
    }
}