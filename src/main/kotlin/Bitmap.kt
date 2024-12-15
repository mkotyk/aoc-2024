package com.psyndicate.aoc

data class Coord(val x: Int, val y: Int) {
    operator fun plus(direction: Direction) = Coord(x + direction.dx, y + direction.dy)
    operator fun minus(direction: Direction) = Coord(x - direction.dx, y - direction.dy)
    operator fun plus(other: Coord) = Coord(x + other.x, y + other.y)
    operator fun minus(other: Coord) = Coord(x - other.x, y - other.y)
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

inline fun <reified T> bitmapFromCoords(coords: List<Coord>, filled: T, empty: T): Bitmap<T> {
    val (minX, maxX) = coords.minMax { it.x }
    val (minY, maxY) = coords.minMax { it.y }
    val width = (maxX - minX) + 1
    val height = (maxY - minY + 1)
    return Bitmap<T>(Array<T>(width * height) { empty }, width, height).apply {
        coords.forEach {
            val adjustedPosition = Coord(it.x - minX, it.y - minY)
            this[adjustedPosition] = filled
        }
    }
}