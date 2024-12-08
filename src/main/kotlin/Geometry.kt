package com.psyndicate.aoc

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

val Double.squared: Double get() = this * this
val Double.cubed: Double get() = this * this * this

data class Point(val x: Double, val y: Double) {
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)
    fun distance(other: Point) = sqrt((this.x - other.x).squared + (this.y - other.y).squared)
}

data class Line(val a: Point, val b: Point) {
    val dX: Double get() = b.x - a.x
    val dY: Double get() = b.y - a.y

    fun length() = sqrt(dX * dX + dY * dY)
    fun slope(): Double = dY / dX
    fun intercept(): Double = a.y - (slope() * a.x)

    fun intersects(other: Line): Point? {
        val m1 = this.slope()
        val b1 = this.intercept()
        val m2 = other.slope()
        val b2 = other.intercept()
        if (m1 == m2) return null
        val x = (b2 - b1) / (m1 - m2)
        return if (min(a.x, b.x) <= x && x <= max(a.x, b.x) && min(other.a.x, other.b.x) <= x && x <= max(
                other.a.x,
                other.b.x
            )
        )
            Point(x, y = (m1 * x + b1))
        else null
    }
}

data class Circle(val center: Point, val radius: Double) {
    fun contains(point: Point): Boolean = (point.x - center.x).squared + (point.y - center.y).squared < radius.squared
    fun intercepts(line: Line): Set<Point> {
        // Quadratic coefficients for solving (x - h)^2 + (mx + c - k)^2 = r^2
        val m = line.slope()
        val c = line.intercept()
        val A = 1 + m * m
        val B = 2 * (m * (c - center.y) -center.x)
        val C = center.x.squared + (c - center.y).squared - radius.squared

        // Calculate the discriminant
        val discriminant = B * B - 4 * A * C

        return when {
            discriminant < 0 -> emptySet() // No real solutions (no intersection)
            discriminant == 0.0 -> { // One solution (tangent)
                val x = -B / (2 * A)
                val y = m * x + c
                setOf(Point(x, y))
            }
            else -> { // Two solutions (two points of intersection)
                val sqrtDiscriminant = sqrt(discriminant)
                val x1 = (-B + sqrtDiscriminant) / (2 * A)
                val x2 = (-B - sqrtDiscriminant) / (2 * A)
                val y1 = m * x1 + c
                val y2 = m * x2 + c
                setOf(Point(x1, y1), Point(x2, y2))
            }
        }
    }
}

data class Rectangle(val topLeft: Point, val bottomRight: Point) {
    fun contains(point: Point): Boolean = point.x >= bottomRight.x && point.x <= bottomRight.x
            && point.y >= bottomRight.y && point.y <= bottomRight.y
    fun intercepts(other: Line): Set<Point> = segments.mapNotNull{ it.intersects(other) }.toSet()
    val segments: Set<Line> get() = setOf(
        Line(Point(topLeft.x, topLeft.y), Point(bottomRight.x, topLeft.y)),         // Top
        Line(Point(bottomRight.x, topLeft.y), Point(bottomRight.x, bottomRight.y)), // Right
        Line(Point(topLeft.x, bottomRight.y), Point(bottomRight.x, bottomRight.y)), // Bottom
        Line(Point(topLeft.x, topLeft.y), Point(topLeft.x, bottomRight.y)),         // Left
    )
}

data class ClosedPoly(val vertices: Set<Point>) {
    fun intercepts(other: Line): Set<Point> = (
            vertices.zipWithNext { a, b -> Line(a, b).intersects(other) }.toSet() +
                    Line(vertices.last(), vertices.first()).intersects(other)
            )
        .filterNotNull()
        .toSet()
}