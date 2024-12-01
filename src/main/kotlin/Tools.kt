package com.psyndicate.aoc

fun readResourceAsText(path: String): String = object {}::class.java.getResource(path)?.readText()
    ?:  error("Failed to load resource [$path]")

fun <T> List<T>.permute(i: List<T> = emptyList()): List<List<T>> = if (isEmpty()) listOf(i) else
    fold(emptyList()) { acc, e -> acc + (this - e).permute(i + e) }

fun <T> List<T>.permute(i: List<T> = emptyList(), block: (List<T>) -> Unit): Unit =
    if (isEmpty()) block(i) else forEach { e -> (this - e).permute(i + e, block) }

fun <T, A, B> Iterable<T>.unzip(block: (T) -> Pair<A, B>): Pair<List<A>, List<B>> {
    val listA = mutableListOf<A>()
    val listB = mutableListOf<B>()
    forEach {
        val (a, b) = block(it)
        listA.add(a)
        listB.add(b)
    }
    return listA to listB
}

fun String.parseIntegers(): List<Int> = split(' ').filter { it.length > 0 }.map { it.toInt() }

fun <A> List<A>.toPair(): Pair<A,A> = this[0] to this[1]