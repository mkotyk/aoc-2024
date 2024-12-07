package com.psyndicate.aoc

class MemoCache<K, V> {
    private val cache = hashMapOf<K, V>()

    fun memoize(key: K, block: () -> V): V = cache[key] ?: block().also { cache[key] = it }

    fun reset() = cache.clear()
}