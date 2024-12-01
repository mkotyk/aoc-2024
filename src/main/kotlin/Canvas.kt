package com.psyndicate.aoc

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage

fun canvas(
    width: Int = 640,
    height: Int = 480,
    backgroundColour: Color = Color.WHITE,
    block: Graphics2D.(width: Int, height: Int) -> Unit,
): BufferedImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val graphics = image.createGraphics()
    with(graphics) {
        background = backgroundColour
        clearRect(0, 0, width, height)
        setRenderingHint(
            java.awt.RenderingHints.KEY_ANTIALIASING,
            java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        )
        block(width, height)
    }
    graphics.dispose()
    return image
}

fun bitmap(
    width: Int = 640,
    height: Int = 480,
    block: BufferedImage.(width: Int, height: Int) -> Unit,
): BufferedImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    block(image, width, height)
    return image
}