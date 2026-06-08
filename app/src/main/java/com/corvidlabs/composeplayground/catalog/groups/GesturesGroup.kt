package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/// Components in the [ComponentGroup.Gestures] group: click, drag, swipe, and scroll handling.
internal val gesturesComponents: List<Component> get() = listOf(
    clickableComponent,
    draggableComponent,
    transformableComponent,
    scrollComponent,
    swipeToDismissComponent,
    pullToRefreshComponent
)

private val clickableComponent = Component(
    id = "clickable",
    name = "Clickable",
    group = ComponentGroup.Gestures,
    summary = "Modifier.clickable and combinedClickable for taps and long presses",
    description = "Modifier.clickable makes any composable respond to taps. combinedClickable " +
        "adds long-press and double-tap callbacks, letting a plain Box behave like a button.",
    docUrl = "https://developer.android.com/jetpack/compose/touch-input/pointer-input/tap-and-press",
    related = listOf("draggable", "transformable"),
    examples = listOf(
        CodeExample(
            title = "Tap and long press",
            description = "combinedClickable distinguishes a tap from a long press.",
            code = """
                var label by remember { mutableStateOf("Tap or long-press me") }
                var count by remember { mutableIntStateOf(0) }
                Box(
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .combinedClickable(
                            onClick = { count++; label = "Tapped ${'$'}count" },
                            onLongClick = { label = "Long-pressed!" }
                        )
                        .padding(24.dp)
                ) {
                    Text(label)
                }
            """,
            demo = { ClickableDemo() }
        )
    )
)

private val draggableComponent = Component(
    id = "draggable",
    name = "Draggable",
    group = ComponentGroup.Gestures,
    summary = "Drag a box along one axis with rememberDraggableState",
    description = "Modifier.draggable reports drag deltas on a single orientation. Track the " +
        "accumulated offset in state, clamp it to a range, and apply it with Modifier.offset.",
    docUrl = "https://developer.android.com/jetpack/compose/touch-input/pointer-input/drag-swipe-fling",
    related = listOf("clickable", "transformable"),
    examples = listOf(
        CodeExample(
            title = "Horizontal drag",
            description = "The handle follows your finger, clamped to a 0..220 dp range.",
            code = """
                var offset by remember { mutableFloatStateOf(0f) }
                Box(
                    Modifier
                        .offset { IntOffset(offset.roundToInt(), 0) }
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                offset = (offset + delta).coerceIn(0f, 600f)
                            }
                        )
                )
            """,
            demo = { DraggableDemo() }
        )
    )
)

private val transformableComponent = Component(
    id = "transformable",
    name = "Transformable",
    group = ComponentGroup.Gestures,
    summary = "Pan, zoom, and rotate with rememberTransformableState",
    description = "Modifier.transformable feeds combined pan, zoom, and rotation gestures into a " +
        "single callback. Store the result in state and render it through a graphicsLayer.",
    docUrl = "https://developer.android.com/jetpack/compose/touch-input/pointer-input/multitouch",
    related = listOf("clickable", "draggable"),
    examples = listOf(
        CodeExample(
            title = "Pan, zoom, rotate",
            description = "Use two fingers to scale and rotate; drag to pan.",
            code = """
                var scale by remember { mutableFloatStateOf(1f) }
                var rotation by remember { mutableFloatStateOf(0f) }
                var offsetX by remember { mutableFloatStateOf(0f) }
                var offsetY by remember { mutableFloatStateOf(0f) }
                val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                    scale = (scale * zoomChange).coerceIn(0.5f, 4f)
                    rotation += rotationChange
                    offsetX += panChange.x
                    offsetY += panChange.y
                }
                Box(
                    Modifier
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            rotationZ = rotation,
                            translationX = offsetX,
                            translationY = offsetY
                        )
                        .transformable(state)
                        .size(96.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            """,
            demo = { TransformableDemo() }
        )
    )
)

private val scrollComponent = Component(
    id = "scroll",
    name = "Scroll",
    group = ComponentGroup.Gestures,
    summary = "Modifier.verticalScroll over a fixed-height Column",
    description = "For a known, modest number of items a plain Column made scrollable with " +
        "Modifier.verticalScroll(rememberScrollState()) is simpler than a lazy list.",
    docUrl = "https://developer.android.com/jetpack/compose/touch-input/pointer-input/scroll",
    related = listOf("lazy-column"),
    examples = listOf(
        CodeExample(
            title = "Scrollable Column",
            description = "All rows are composed eagerly; the viewport is capped at 180 dp.",
            code = """
                val scroll = rememberScrollState()
                Column(
                    Modifier
                        .height(180.dp)
                        .verticalScroll(scroll)
                ) {
                    repeat(20) { index ->
                        Text("Row ${'$'}index", Modifier.padding(8.dp))
                    }
                }
            """,
            demo = { ScrollDemo() }
        )
    )
)

private val swipeToDismissComponent = Component(
    id = "swipe-to-dismiss",
    name = "Swipe to Dismiss",
    group = ComponentGroup.Gestures,
    summary = "Swipe list items away with SwipeToDismissBox",
    description = "SwipeToDismissBox wraps each row with a swipe gesture and a background that " +
        "shows behind it. Confirm the dismissal to remove the backing item from the list.",
    docUrl = "https://developer.android.com/jetpack/compose/components/swipe-to-dismiss",
    related = listOf("pull-to-refresh", "lazy-column"),
    examples = listOf(
        CodeExample(
            title = "Dismiss to remove",
            description = "Swipe a row in either direction to delete it.",
            code = """
                val items = remember { mutableStateListOf("Email one", "Email two", "Email three") }
                Column {
                    items.forEach { item ->
                        val state = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value ->
                                if (value != SwipeToDismissBoxValue.Settled) {
                                    items.remove(item)
                                    true
                                } else {
                                    false
                                }
                            }
                        )
                        SwipeToDismissBox(
                            state = state,
                            backgroundContent = {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.errorContainer)
                                )
                            }
                        ) {
                            Text(item, Modifier.fillMaxWidth().padding(16.dp))
                        }
                    }
                }
            """,
            demo = { SwipeToDismissDemo() }
        )
    )
)

private val pullToRefreshComponent = Component(
    id = "pull-to-refresh",
    name = "Pull to Refresh",
    group = ComponentGroup.Gestures,
    summary = "Pull down to trigger a refresh with PullToRefreshBox",
    description = "PullToRefreshBox shows a refresh indicator when the content is pulled past the " +
        "threshold. Drive it with an isRefreshing flag that you flip off when the work completes.",
    docUrl = "https://developer.android.com/jetpack/compose/components/pull-to-refresh",
    related = listOf("swipe-to-dismiss", "lazy-column"),
    examples = listOf(
        CodeExample(
            title = "Pull to refresh",
            description = "Pull down; the list reloads after a brief simulated fetch.",
            code = """
                var isRefreshing by remember { mutableStateOf(false) }
                var generation by remember { mutableIntStateOf(0) }
                LaunchedEffect(isRefreshing) {
                    if (isRefreshing) {
                        delay(1200)
                        generation++
                        isRefreshing = false
                    }
                }
                PullToRefreshBox(
                    isRefreshing = isRefreshing,
                    onRefresh = { isRefreshing = true }
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        repeat(6) { index ->
                            Text("Item ${'$'}generation.${'$'}index", Modifier.padding(12.dp))
                        }
                    }
                }
            """,
            demo = { PullToRefreshDemo() }
        )
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ClickableDemo() {
    var label by remember { mutableStateOf("Tap or long-press me") }
    var count by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .combinedClickable(
                onClick = {
                    count++
                    label = "Tapped $count"
                },
                onLongClick = { label = "Long-pressed!" }
            )
            .padding(24.dp)
    ) {
        Text(label)
    }
}

@Composable
private fun DraggableDemo() {
    var offset by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset { IntOffset(offset.roundToInt(), 0) }
                .padding(8.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offset = (offset + delta).coerceIn(0f, 600f)
                    }
                )
        )
    }
}

@Composable
private fun TransformableDemo() {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale = (scale * zoomChange).coerceIn(0.5f, 4f)
        rotation += rotationChange
        offsetX += panChange.x
        offsetY += panChange.y
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .transformable(state)
                .size(96.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
private fun ScrollDemo() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(scroll)
    ) {
        repeat(20) { index ->
            Text(
                text = "Row $index",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeToDismissDemo() {
    val items = remember { mutableStateListOf("Email one", "Email two", "Email three") }
    Column(modifier = Modifier.fillMaxWidth()) {
        if (items.isEmpty()) {
            Text(
                text = "All cleared — nothing left to swipe.",
                modifier = Modifier.padding(16.dp)
            )
        }
        items.forEach { item ->
            key(item) {
                val state = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (value != SwipeToDismissBoxValue.Settled) {
                            items.remove(item)
                            true
                        } else {
                            false
                        }
                    }
                )
                SwipeToDismissBox(
                    state = state,
                    backgroundContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(MaterialTheme.colorScheme.errorContainer)
                        )
                    }
                ) {
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PullToRefreshDemo() {
    var isRefreshing by remember { mutableStateOf(false) }
    var generation by remember { mutableIntStateOf(0) }
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1200)
            generation++
            isRefreshing = false
        }
    }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { isRefreshing = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            repeat(6) { index ->
                Text(
                    text = "Item $generation.$index",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}
