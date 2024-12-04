package com.psyndicate.aoc

enum class Direction(val dx: Int, val dy: Int) {
    RIGHT(1, 0),
    LEFT(-1, 0),
    DOWN(0,1),
    UP(0,-1),
    LEFT_UP(-1,-1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1,1),
    LEFT_DOWN(-1,1)
}