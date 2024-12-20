package com.psyndicate.aoc

enum class Direction(val dx: Int, val dy: Int) {
    RIGHT(1, 0),
    LEFT(-1, 0),
    DOWN(0, 1),
    UP(0, -1),
    LEFT_UP(-1, -1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1, 1),
    LEFT_DOWN(-1, 1)
}

val CardinalDirections = setOf(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)

fun Direction.rotate90(clockwise: Boolean): Direction {
    return if (clockwise) {
        when (this) {
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
            Direction.RIGHT -> Direction.DOWN
            Direction.LEFT_UP -> Direction.RIGHT_UP
            Direction.RIGHT_UP -> Direction.RIGHT_DOWN
            Direction.RIGHT_DOWN -> Direction.LEFT_DOWN
            Direction.LEFT_DOWN -> Direction.LEFT_UP
        }
    } else {
        when (this) {
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.DOWN
            Direction.RIGHT -> Direction.UP
            Direction.LEFT_UP -> Direction.LEFT_DOWN
            Direction.RIGHT_UP -> Direction.LEFT_UP
            Direction.RIGHT_DOWN -> Direction.RIGHT_UP
            Direction.LEFT_DOWN -> Direction.RIGHT_DOWN
        }
    }
}

operator fun Direction.times(magnitude: Int) = Coord(dx * magnitude, dy * magnitude)

fun Direction.opposite(): Direction = when (this) {
    Direction.UP -> Direction.DOWN
    Direction.DOWN -> Direction.UP
    Direction.LEFT -> Direction.RIGHT
    Direction.RIGHT -> Direction.LEFT
    Direction.LEFT_UP -> Direction.RIGHT_DOWN
    Direction.RIGHT_UP -> Direction.LEFT_DOWN
    Direction.RIGHT_DOWN -> Direction.LEFT_UP
    Direction.LEFT_DOWN -> Direction.RIGHT_UP
}