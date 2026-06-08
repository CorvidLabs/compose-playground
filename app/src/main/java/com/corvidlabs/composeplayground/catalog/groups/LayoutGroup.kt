package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Layout] group: Row, Column, Box, and arrangement primitives.
internal val layoutComponents: List<Component> get() = listOf(
    rowColumnComponent,
    boxComponent,
    spacerComponent,
    surfaceComponent
)

private val rowColumnComponent = Component(
    id = "row-column",
    name = "Row & Column",
    group = ComponentGroup.Layout,
    summary = "Lay children out horizontally or vertically",
    description = "Row and Column are the workhorse layouts. Children are placed along the main " +
        "axis; weight distributes leftover space proportionally, and arrangement controls the gaps " +
        "between and around them.",
    docUrl = "https://developer.android.com/jetpack/compose/layouts/basics",
    related = listOf("box", "spacer"),
    examples = listOf(
        CodeExample(
            title = "Weighted Row",
            description = "Weights split the available width — here 1:2:1.",
            code = """
                Row(modifier = Modifier.fillMaxWidth()) {
                    WeightBox(weight = 1f, color = MaterialTheme.colorScheme.primary)
                    WeightBox(weight = 2f, color = MaterialTheme.colorScheme.secondary)
                    WeightBox(weight = 1f, color = MaterialTheme.colorScheme.tertiary)
                }
            """,
            demo = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    WeightBox(weight = 1f, color = MaterialTheme.colorScheme.primary)
                    WeightBox(weight = 2f, color = MaterialTheme.colorScheme.secondary)
                    WeightBox(weight = 1f, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        ),
        CodeExample(
            title = "Column arrangement",
            description = "SpaceBetween pushes the first and last child to the edges.",
            code = """
                Column(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Top")
                    Text("Middle")
                    Text("Bottom")
                }
            """,
            demo = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Top")
                        Text("Middle")
                        Text("Bottom")
                    }
                }
            }
        )
    )
)

private val boxComponent = Component(
    id = "box",
    name = "Box",
    group = ComponentGroup.Layout,
    summary = "Stack children and align them with Modifier.align",
    description = "Box draws its children on top of one another, sized to the largest. Each child " +
        "positions itself within the Box using Modifier.align, making it ideal for overlays, " +
        "badges, and corner-anchored content.",
    docUrl = "https://developer.android.com/jetpack/compose/layouts/basics#box",
    related = listOf("row-column", "spacer"),
    examples = listOf(
        CodeExample(
            title = "Aligned children",
            description = "Three labels anchored to different corners of the same Box.",
            code = """
                Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                    Text("TopStart", Modifier.align(Alignment.TopStart))
                    Text("Center", Modifier.align(Alignment.Center))
                    Text("BottomEnd", Modifier.align(Alignment.BottomEnd))
                }
            """,
            demo = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(16.dp)
                ) {
                    Text("TopStart", modifier = Modifier.align(Alignment.TopStart))
                    Text("Center", modifier = Modifier.align(Alignment.Center))
                    Text("BottomEnd", modifier = Modifier.align(Alignment.BottomEnd))
                }
            }
        )
    )
)

private val spacerComponent = Component(
    id = "spacer",
    name = "Spacer",
    group = ComponentGroup.Layout,
    summary = "Push siblings apart with flexible empty space",
    description = "Spacer is an empty layout used to insert gaps. Given a weight inside a Row or " +
        "Column it absorbs all leftover space, shoving the elements on either side to opposite ends.",
    docUrl = "https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#Spacer(androidx.compose.ui.Modifier)",
    related = listOf("row-column", "box"),
    examples = listOf(
        CodeExample(
            title = "Push apart",
            description = "A weighted Spacer separates the leading and trailing text.",
            code = """
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Left")
                    Spacer(Modifier.weight(1f))
                    Text("Right")
                }
            """,
            demo = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Left")
                    Spacer(Modifier.weight(1f))
                    Text("Right")
                }
            }
        )
    )
)

private val surfaceComponent = Component(
    id = "surface",
    name = "Surface",
    group = ComponentGroup.Layout,
    summary = "Elevation, shape, and color for a sheet of content",
    description = "Surface is the foundational container behind most Material components. It applies " +
        "a background color, clips to a shape, and raises content with tonal elevation that tints " +
        "the surface toward the primary color.",
    docUrl = "https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Surface(androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.foundation.BorderStroke,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp,kotlin.Function0)",
    related = listOf("box", "row-column"),
    examples = listOf(
        CodeExample(
            title = "Elevation, shape & color",
            description = "Different tonal elevations, shapes, and container colors.",
            code = """
                Surface(tonalElevation = 4.dp, shape = RoundedCornerShape(12.dp)) {
                    Text("Elevated", Modifier.padding(16.dp))
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text("Primary container", Modifier.padding(16.dp))
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text("Secondary container", Modifier.padding(16.dp))
                }
            """,
            demo = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Surface(
                        tonalElevation = 4.dp,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Elevated", modifier = Modifier.padding(16.dp))
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text("Primary container", modifier = Modifier.padding(16.dp))
                    }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text("Secondary container", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        )
    )
)

/// A weighted, colored block used to visualize Row weight distribution.
@Composable
private fun RowScope.WeightBox(weight: Float, color: Color) {
    Box(
        modifier = Modifier
            .weight(weight)
            .height(56.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
    )
}
