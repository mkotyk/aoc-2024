package com.psyndicate.aoc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <A, B> Iterable<A>.parallelMap(f: suspend (A) -> B): Iterable<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

suspend fun <A, B> Sequence<A>.parallelMap(f: suspend (A) -> B): Iterable<B> = coroutineScope {
    map { async { f(it) } }.toList().awaitAll()
}

suspend fun <A, B> Iterable<A>.parallelFlatMap(f: suspend (A) -> Iterable<B>): Iterable<B> = coroutineScope {
    map { async { f(it) } }.flatMap { deferred -> deferred.await() }
}
