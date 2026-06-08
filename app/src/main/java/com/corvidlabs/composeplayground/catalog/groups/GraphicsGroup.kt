package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Graphics] group: Canvas, brushes, shapes, and clipping.
internal val graphicsComponents: List<Component> get() = listOf(
    canvasComponent,
    gradientsComponent,
    shapesComponent,
    drawModifiersComponent
)

private val canvasComponent = Component(
    id = "canvas",
    name = "Canvas",
    group = ComponentGroup.Graphics,
    summary = "Draw lines, circles, rects, arcs, and paths",
    description = "Canvas gives you a DrawScope to render custom graphics imperatively. The " +
        "scope exposes drawLine, drawCircle, drawRect, drawArc, and drawPath, all working in " +
        "pixels with the composable's own size available as this.size.",
    docUrl = "https://developer.android.com/jetpack/compose/graphics/draw/overview",
    related = listOf("gradients", "shapes", "draw-modifiers"),
    examples = listOf(
        CodeExample(
            title = "Primitive shapes",
            description = "A single DrawScope draws a line, circle, rectangle, arc, and a triangle path.",
            code = """
                Canvas(Modifier.fillMaxWidth().height(160.dp)) {
                    drawLine(
                        color = Color(0xFF6750A4),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = 4f
                    )
                    drawCircle(Color(0xFF7D5260), radius = 36f, center = Offset(60f, 60f))
                    drawRect(Color(0xFF625B71), topLeft = Offset(120f, 24f), size = Size(72f, 72f))
                    drawArc(
                        color = Color(0xFF386A20),
                        startAngle = 0f,
                        sweepAngle = 270f,
                        useCenter = true,
                        topLeft = Offset(size.width - 120f, 24f),
                        size = Size(80f, 80f)
                    )
                    val triangle = Path().apply {
                        moveTo(size.width / 2f, size.height - 16f)
                        lineTo(size.width / 2f - 48f, size.height - 80f)
                        lineTo(size.width / 2f + 48f, size.height - 80f)
                        close()
                    }
                    drawPath(triangle, Color(0xFFB3261E))
                }
            """,
            demo = { CanvasDemo() }
        )
    )
)

private val gradientsComponent = Component(
    id = "gradients",
    name = "Gradients",
    group = ComponentGroup.Graphics,
    summary = "Linear, radial, and sweep brushes",
    description = "A Brush describes how to paint an area. Brush.linearGradient interpolates " +
        "along a vector, Brush.radialGradient fans out from a center, and Brush.sweepGradient " +
        "sweeps around it. Any brush can fill a composable via Modifier.background.",
    docUrl = "https://developer.android.com/jetpack/compose/graphics/draw/brush",
    related = listOf("canvas", "shapes", "draw-modifiers"),
    examples = listOf(
        CodeExample(
            title = "Three brush types",
            description = "Each box clips to a rounded shape and fills with a different gradient.",
            code = """
                val colors = listOf(Color(0xFF6750A4), Color(0xFF7D5260), Color(0xFF386A20))
                Box(
                    Modifier.size(80.dp).clip(RoundedCornerShape(16.dp))
                        .background(Brush.linearGradient(colors))
                )
                Box(
                    Modifier.size(80.dp).clip(RoundedCornerShape(16.dp))
                        .background(Brush.radialGradient(colors))
                )
                Box(
                    Modifier.size(80.dp).clip(RoundedCornerShape(16.dp))
                        .background(Brush.sweepGradient(colors))
                )
            """,
            demo = { GradientsDemo() }
        )
    )
)

private val shapesComponent = Component(
    id = "shapes",
    name = "Shapes & Clipping",
    group = ComponentGroup.Graphics,
    summary = "Rounded, circle, and cut-corner clips",
    description = "Shapes define an outline used for clipping and backgrounds. " +
        "RoundedCornerShape softens corners, CircleShape produces a pill or circle, and " +
        "CutCornerShape bevels them. Apply with Modifier.clip then paint with a background.",
    docUrl = "https://developer.android.com/jetpack/compose/graphics/draw/shapes",
    related = listOf("canvas", "gradients", "draw-modifiers"),
    examples = listOf(
        CodeExample(
            title = "Clip outlines",
            description = "The same colored box clipped three different ways.",
            code = """
                Box(
                    Modifier.size(80.dp).clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )
                Box(
                    Modifier.size(80.dp).clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                )
                Box(
                    Modifier.size(80.dp).clip(CutCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                )
            """,
            demo = { ShapesDemo() }
        )
    )
)

private val drawModifiersComponent = Component(
    id = "draw-modifiers",
    name = "Draw Modifiers",
    group = ComponentGroup.Graphics,
    summary = "drawBehind, drawWithContent, and border",
    description = "Draw modifiers attach custom rendering to any composable. Modifier.drawBehind " +
        "paints under the content, Modifier.drawWithContent lets you sequence drawing around " +
        "drawContent(), and Modifier.border strokes a shaped outline.",
    docUrl = "https://developer.android.com/jetpack/compose/graphics/draw/modifiers",
    related = listOf("canvas", "gradients", "shapes"),
    examples = listOf(
        CodeExample(
            title = "Accent behind text",
            description = "drawBehind paints a highlight bar under a label.",
            code = """
                val accent = MaterialTheme.colorScheme.primary
                Text(
                    text = "Highlighted",
                    modifier = Modifier
                        .drawBehind {
                            drawLine(
                                color = accent,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 6f,
                                cap = StrokeCap.Round
                            )
                        }
                        .padding(bottom = 4.dp)
                )
            """,
            demo = { DrawBehindDemo() }
        ),
        CodeExample(
            title = "Overlay & border",
            description = "drawWithContent tints over the content; border strokes a shaped edge.",
            code = """
                Text(
                    text = "Tinted",
                    modifier = Modifier
                        .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                        .drawWithContent {
                            drawContent()
                            drawRect(Color(0x223F51B5))
                        }
                )
            """,
            demo = { DrawWithContentDemo() }
        )
    )
)

@Composable
private fun CanvasDemo() {
    Canvas(modifier = Modifier.fillMaxWidth().height(160.dp)) {
        drawLine(
            color = Color(0xFF6750A4),
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = 4f
        )
        drawCircle(color = Color(0xFF7D5260), radius = 36f, center = Offset(60f, 60f))
        drawRect(color = Color(0xFF625B71), topLeft = Offset(120f, 24f), size = Size(72f, 72f))
        drawArc(
            color = Color(0xFF386A20),
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(size.width - 120f, 24f),
            size = Size(80f, 80f)
        )
        val triangle = Path().apply {
            moveTo(size.width / 2f, size.height - 16f)
            lineTo(size.width / 2f - 48f, size.height - 80f)
            lineTo(size.width / 2f + 48f, size.height - 80f)
            close()
        }
        drawPath(path = triangle, color = Color(0xFFB3261E))
    }
}

@Composable
private fun GradientsDemo() {
    val colors = listOf(Color(0xFF6750A4), Color(0xFF7D5260), Color(0xFF386A20))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.linearGradient(colors))
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.radialGradient(colors))
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.sweepGradient(colors))
        )
    }
}

@Composable
private fun ShapesDemo() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CutCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.tertiary)
        )
    }
}

@Composable
private fun DrawBehindDemo() {
    val accent = MaterialTheme.colorScheme.primary
    Text(
        text = "Highlighted",
        modifier = Modifier
            .drawBehind {
                drawLine(
                    color = accent,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 6f,
                    cap = StrokeCap.Round
                )
            }
            .padding(bottom = 4.dp)
    )
}

@Composable
private fun DrawWithContentDemo() {
    val outline = MaterialTheme.colorScheme.outline
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Tinted",
            modifier = Modifier
                .border(2.dp, outline, RoundedCornerShape(8.dp))
                .padding(8.dp)
                .drawWithContent {
                    drawContent()
                    drawRect(color = Color(0x223F51B5))
                }
        )
    }
}
