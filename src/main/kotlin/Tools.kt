package com.psyndicate.aoc

import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun readResourceAsText(path: String): String = object {}::class.java.getResource(path)?.readText()
    ?: error("Failed to load resource [$path]")

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

fun String.parseIntegers(): List<Int> = split(' ').filter { it.isNotEmpty() }.map { it.toInt() }

fun <A> List<A>.toPair(): Pair<A, A> = this[0] to this[1]

fun <T> List<T>.replaceAt(index: Int, replaceBlock: (T) -> T) = buildList<T> {
    this@replaceAt.forEachIndexed { i, a ->
        add(if (i == index) replaceBlock(a) else this@replaceAt[i])
    }
}

fun Long.concat(other: Int): Long {
    val magnitude = floor(log10(other.toDouble())).toInt() + 1
    return this * (10.0.pow(magnitude.toDouble())).toInt() + other
}

fun Long.digits(): Int = floor(log10(this.toDouble())).toInt() + 1

fun <T, R> Iterable<T>.square(block: (T, T) -> R): List<R> = this.flatMap { a -> this.map { b -> block(a, b) } }

fun <T, R : Comparable<R>> Iterable<T>.minMax(block: (T) -> R): Pair<R, R> {
    var min: R? = null
    var max: R? = null
    forEach {
        val r = block(it)
        if (max == null || r > max!!) max = r
        if (min === null || r < min!!) min = r
    }
    return min!! to max!!
}