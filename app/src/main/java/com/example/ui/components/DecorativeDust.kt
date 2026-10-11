package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private data class DustParticle(
    val xFraction: Float,
    val yFraction: Float,
    val radius: Float,
    val alpha: Float,
    val isGold: Boolean = false
)

@Composable
fun DecorativeDustCanvas(
    modifier: Modifier = Modifier,
    particleCount: Int = 45,
    seed: Int = 42
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dust_pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_val"
    )

    val particles = remember(particleCount, seed) {
        val rand = Random(seed)
        List(particleCount) {
            DustParticle(
                xFraction = rand.nextFloat(),
                yFraction = rand.nextFloat(),
                radius = rand.nextFloat() * 2.8f + 0.8f,
                alpha = rand.nextFloat() * 0.45f + 0.15f,
                isGold = rand.nextFloat() > 0.75f
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        for (p in particles) {
            val drawColor = if (p.isGold) {
                Color(0xFFD4A373).copy(alpha = (p.alpha * pulse).coerceIn(0.1f, 0.7f))
            } else {
                Color(0xFFEFEFEF).copy(alpha = (p.alpha * pulse).coerceIn(0.1f, 0.6f))
            }

            drawCircle(
                color = drawColor,
                radius = p.radius * pulse,
                center = Offset(p.xFraction * width, p.yFraction * height)
            )
        }
    }
}
