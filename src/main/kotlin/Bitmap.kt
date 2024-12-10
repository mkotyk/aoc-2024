package com.psyndicate.aoc

data class Coord(val x: Int, val y: Int) {
    operator fun plus(direction: Direction) = Coord(x + direction.dx, y + direction.dy)
    fun toPoint() = Point(x.toDouble(), y.toDouble())
}

fun Point.toCoord() = Coord(x.toInt(), y.toInt())

class Bitmap<T>(val data: Array<T>, val width: Int, val height: Int) {
    operator fun get(x: Int, y: Int): T? = if (x in 0..<width && y in 0..<height) data[x + y * width] else null
    operator fun set(x: Int, y: Int, c: T) {
        if (x in 0..<width && y in 0..<height) data[x + y * width] = c
    }

    operator fun get(coord: Coord): T? = this[coord.x, coord.y]
    operator fun set(coord: Coord, c: T) {
        this[coord.x, coord.y] = c
    }

    fun coords(): Sequence<Coord> = sequence {
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                yield(Coord(x, y))
            }
        }
    }

    fun isInBounds(coords: Coord): Boolean = (coords.x in 0..<width && coords.y in 0..<height)

    override fun toString(): String {
        return (data.indices step width).joinToString("\n") { x ->
            data.copyOfRange(x, x + width).joinToString("")
        }
    }
}

fun bitmapFromString(input: String): Bitmap<Char> = Bitmap<Char>(
    data = input.filter { it != '\n' }.toCharArray().toTypedArray(),
    width = input.indexOf('\n'),
    height = input.count { it == '\n' } + 1
)