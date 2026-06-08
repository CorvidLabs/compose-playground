package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Animation] group: state, visibility, content, and transitions.
internal val animationComponents: List<Component> get() = listOf(
    animateAsStateComponent,
    animatedVisibilityComponent,
    animatedContentComponent,
    crossfadeComponent,
    infiniteTransitionComponent
)

private val animateAsStateComponent = Component(
    id = "animate-as-state",
    name = "animate*AsState",
    group = ComponentGroup.Animation,
    summary = "Animate a single value whenever its target changes",
    description = "The animate*AsState family interpolates a value toward a new target whenever " +
        "that target changes. Swap in animateColorAsState, animateDpAsState, and friends to " +
        "drive size, color, offset, or alpha — optionally with a spring for a bouncy feel.",
    docUrl = "https://developer.android.com/jetpack/compose/animation/value-based",
    related = listOf("animated-visibility", "animated-content", "infinite-transition"),
    examples = listOf(
        CodeExample(
            title = "Color & size",
            description = "Tap to toggle; color and size animate to their new targets.",
            code = """
                var toggled by remember { mutableStateOf(false) }
                val color by animateColorAsState(
                    targetValue = if (toggled) Color(0xFF6750A4) else Color(0xFF7D5260),
                    label = "color"
                )
                val size by animateDpAsState(
                    targetValue = if (toggled) 120.dp else 72.dp,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "size"
                )
                Box(Modifier.size(size).clip(RoundedCornerShape(16.dp)).background(color))
                Button(onClick = { toggled = !toggled }) { Text("Toggle") }
            """,
            demo = { AnimateAsStateDemo() }
        )
    )
)

private val animatedVisibilityComponent = Component(
    id = "animated-visibility",
    name = "AnimatedVisibility",
    group = ComponentGroup.Animation,
    summary = "Animate content as it enters and leaves composition",
    description = "AnimatedVisibility animates the appearance and disappearance of its content. " +
        "Combine enter transitions like fadeIn and expandVertically with matching exits such as " +
        "fadeOut and shrinkVertically to control how content slides into view.",
    docUrl = "https://developer.android.com/jetpack/compose/animation/composables-modifiers",
    related = listOf("animate-as-state", "animated-content", "crossfade"),
    examples = listOf(
        CodeExample(
            title = "Fade & expand",
            description = "Toggle to fade and expand the panel in and out.",
            code = """
                var visible by remember { mutableStateOf(true) }
                Button(onClick = { visible = !visible }) {
                    Text(if (visible) "Hide" else "Show")
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Box(Modifier.fillMaxWidth().size(96.dp).background(Color(0xFF6750A4)))
                }
            """,
            demo = { AnimatedVisibilityDemo() }
        )
    )
)

private val animatedContentComponent = Component(
    id = "animated-content",
    name = "AnimatedContent",
    group = ComponentGroup.Animation,
    summary = "Animate the swap between two content states",
    description = "AnimatedContent animates the transition whenever its targetState changes, " +
        "cross-fading (and optionally sliding) between the old and new content. It is well suited " +
        "to swapping a number, label, or whole layout in response to state.",
    docUrl = "https://developer.android.com/jetpack/compose/animation/composables-modifiers",
    related = listOf("crossfade", "animated-visibility", "animate-as-state"),
    examples = listOf(
        CodeExample(
            title = "Counter",
            description = "The number animates as it changes with the +/- buttons.",
            code = """
                var count by remember { mutableIntStateOf(0) }
                Row {
                    Button(onClick = { count-- }) { Text("-") }
                    AnimatedContent(targetState = count, label = "count") { value ->
                        Text("${'$'}value", style = MaterialTheme.typography.displaySmall)
                    }
                    Button(onClick = { count++ }) { Text("+") }
                }
            """,
            demo = { AnimatedContentDemo() }
        )
    )
)

private val crossfadeComponent = Component(
    id = "crossfade",
    name = "Crossfade",
    group = ComponentGroup.Animation,
    summary = "Cross-fade between mutually exclusive screens",
    description = "Crossfade fades between two layouts based on a state value. It is the simplest " +
        "way to animate a swap between mutually exclusive content, such as paging through a set " +
        "of cards or switching between loading and loaded states.",
    docUrl = "https://developer.android.com/jetpack/compose/animation/composables-modifiers",
    related = listOf("animated-content", "animated-visibility", "infinite-transition"),
    examples = listOf(
        CodeExample(
            title = "Pages",
            description = "Advance through three pages; each cross-fades into the next.",
            code = """
                var page by remember { mutableIntStateOf(0) }
                Crossfade(targetState = page, label = "page") { current ->
                    Box(Modifier.fillMaxWidth().size(96.dp).background(pageColors[current])) {
                        Text("Page ${'$'}{current + 1}")
                    }
                }
                Button(onClick = { page = (page + 1) % 3 }) { Text("Next") }
            """,
            demo = { CrossfadeDemo() }
        )
    )
)

private val infiniteTransitionComponent = Component(
    id = "infinite-transition",
    name = "InfiniteTransition",
    group = ComponentGroup.Animation,
    summary = "Loop an animation indefinitely",
    description = "rememberInfiniteTransition drives one or more values that animate forever. " +
        "Pair it with infiniteRepeatable and RepeatMode.Reverse to ping-pong a value back and " +
        "forth — perfect for pulsing, breathing, or shimmering effects.",
    docUrl = "https://developer.android.com/jetpack/compose/animation/value-based",
    related = listOf("animate-as-state", "crossfade", "animated-content"),
    examples = listOf(
        CodeExample(
            title = "Pulse",
            description = "An infinitely repeating tween reverses to pulse the alpha.",
            code = """
                val transition = rememberInfiniteTransition(label = "pulse")
                val alpha by transition.animateFloat(
                    initialValue = 1f,
                    targetValue = 0.2f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "alpha"
                )
                Box(
                    Modifier.size(96.dp).alpha(alpha)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF6750A4))
                )
            """,
            demo = { InfiniteTransitionDemo() }
        )
    )
)

@Composable
private fun AnimateAsStateDemo() {
    var toggled by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (toggled) Color(0xFF6750A4) else Color(0xFF7D5260),
        label = "color"
    )
    val boxSize by animateDpAsState(
        targetValue = if (toggled) 120.dp else 72.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "size"
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(RoundedCornerShape(16.dp))
                .background(color)
        )
        Button(onClick = { toggled = !toggled }) { Text("Toggle") }
    }
}

@Composable
private fun AnimatedVisibilityDemo() {
    var visible by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Hide" else "Show")
        }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(96.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF6750A4))
            )
        }
    }
}

@Composable
private fun AnimatedContentDemo() {
    var count by remember { mutableIntStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FilledTonalButton(onClick = { count-- }) { Text("-") }
        AnimatedContent(targetState = count, label = "count") { value ->
            Text(
                text = "$value",
                style = MaterialTheme.typography.displaySmall
            )
        }
        FilledTonalButton(onClick = { count++ }) { Text("+") }
    }
}

@Composable
private fun CrossfadeDemo() {
    val pageColors = listOf(Color(0xFF6750A4), Color(0xFF7D5260), Color(0xFF386A20))
    var page by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Crossfade(targetState = page, label = "page") { current ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(96.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(pageColors[current]),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Page ${current + 1}",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(onClick = { page = (page + 1) % pageColors.size }) { Text("Next") }
    }
}

@Composable
private fun InfiniteTransitionDemo() {
    val transition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(96.dp)
            .alpha(pulseAlpha)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF6750A4))
    )
}
