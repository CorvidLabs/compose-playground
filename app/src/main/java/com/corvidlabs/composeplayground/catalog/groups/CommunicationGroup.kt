package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup
import kotlinx.coroutines.launch

/// Components in the [ComponentGroup.Communication] group: badges, progress, snackbars,
/// dialogs, and tooltips that surface status and ask for confirmation.
internal val communicationComponents: List<Component> get() = listOf(
    badgeComponent,
    progressComponent,
    snackbarComponent,
    dialogComponent,
    tooltipComponent
)

private val badgeComponent = Component(
    id = "badge",
    name = "Badge",
    group = ComponentGroup.Communication,
    summary = "Dot, count, and overflow badges anchored to an icon",
    description = "A Badge draws attention to a small piece of dynamic status — an unread " +
        "count or a simple dot — on top of another element. Wrap an anchor in a BadgedBox and " +
        "supply the badge; an empty Badge renders as a dot, while content renders as a label.",
    docUrl = "https://developer.android.com/jetpack/compose/components/badge",
    related = listOf("navigation-bar", "progress"),
    examples = listOf(
        CodeExample(
            title = "Dot, count & overflow",
            description = "An empty Badge is a dot; pass Text for a count, capping large values at 99+.",
            code = """
                BadgedBox(badge = { Badge() }) {
                    Icon(Icons.Filled.Email, contentDescription = "Mail")
                }
                BadgedBox(badge = { Badge { Text("8") } }) {
                    Icon(Icons.Filled.Email, contentDescription = "Mail")
                }
                BadgedBox(badge = { Badge { Text("99+") } }) {
                    Icon(Icons.Filled.Email, contentDescription = "Mail")
                }
            """,
            demo = {
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    BadgedBox(badge = { Badge() }) {
                        Icon(Icons.Filled.Email, contentDescription = "Mail")
                    }
                    BadgedBox(badge = { Badge { Text("8") } }) {
                        Icon(Icons.Filled.Email, contentDescription = "Mail")
                    }
                    BadgedBox(badge = { Badge { Text("99+") } }) {
                        Icon(Icons.Filled.Email, contentDescription = "Mail")
                    }
                }
            }
        ),
        CodeExample(
            title = "Live counter",
            description = "Drive the badge from state and hide it when the count is zero.",
            code = """
                var count by remember { mutableIntStateOf(0) }
                BadgedBox(badge = { if (count > 0) Badge { Text("${'$'}count") } }) {
                    Icon(Icons.Filled.Email, contentDescription = "Mail")
                }
                Button(onClick = { count++ }) { Text("Add") }
                OutlinedButton(onClick = { count = 0 }) { Text("Clear") }
            """,
            demo = { BadgeCounter() }
        )
    )
)

private val progressComponent = Component(
    id = "progress",
    name = "Progress Indicators",
    group = ComponentGroup.Communication,
    summary = "Indeterminate and determinate circular & linear progress",
    description = "Progress indicators express an unspecified wait time (indeterminate) or how " +
        "far along a known operation is (determinate). Both circular and linear variants are " +
        "available; pass a progress lambda for the determinate form.",
    docUrl = "https://developer.android.com/jetpack/compose/components/progress",
    related = listOf("snackbar", "badge"),
    examples = listOf(
        CodeExample(
            title = "Indeterminate",
            description = "Omit progress for a continuous, looping animation.",
            code = """
                CircularProgressIndicator()
                LinearProgressIndicator()
            """,
            demo = {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    CircularProgressIndicator()
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        ),
        CodeExample(
            title = "Determinate",
            description = "Animate the progress value and surface it as a percentage.",
            code = """
                var target by remember { mutableStateOf(0f) }
                val progress by animateFloatAsState(targetValue = target, label = "progress")
                LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
                Text("${'$'}{(progress * 100).toInt()}%")
                Button(onClick = { target = (target + 0.2f).coerceAtMost(1f) }) { Text("Advance") }
                OutlinedButton(onClick = { target = 0f }) { Text("Reset") }
            """,
            demo = { DeterminateProgress() }
        )
    )
)

private val snackbarComponent = Component(
    id = "snackbar",
    name = "Snackbar",
    group = ComponentGroup.Communication,
    summary = "Brief, transient messages with an optional action",
    description = "A Snackbar shows a short, low-priority message at the bottom of the screen. " +
        "Host it with a SnackbarHost bound to a SnackbarHostState, then call showSnackbar from a " +
        "coroutine. An action label lets people respond, and the returned result tells you whether " +
        "they tapped it.",
    docUrl = "https://developer.android.com/jetpack/compose/components/snackbar",
    related = listOf("progress", "dialog"),
    examples = listOf(
        CodeExample(
            title = "Show a message",
            description = "Launch showSnackbar from a coroutine scope tied to the composition.",
            code = """
                val hostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Box {
                    Button(onClick = {
                        scope.launch { hostState.showSnackbar("Message sent") }
                    }) { Text("Send") }
                    SnackbarHost(hostState, Modifier.align(Alignment.BottomCenter))
                }
            """,
            demo = { SimpleSnackbar() }
        ),
        CodeExample(
            title = "Action & result",
            description = "Read the SnackbarResult to know whether the action was tapped.",
            code = """
                scope.launch {
                    val result = hostState.showSnackbar(
                        message = "Item deleted",
                        actionLabel = "Undo"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        // restore the item
                    }
                }
            """,
            demo = { ActionSnackbar() }
        )
    )
)

private val dialogComponent = Component(
    id = "dialog",
    name = "Dialog",
    group = ComponentGroup.Communication,
    summary = "AlertDialog confirmations and fully custom dialogs",
    description = "Dialogs interrupt to request a decision or show critical information. " +
        "AlertDialog gives you a ready-made layout with icon, title, body, and confirm/dismiss " +
        "buttons, while the lower-level Dialog lets you place any content — such as a Card — in a " +
        "modal window.",
    docUrl = "https://developer.android.com/jetpack/compose/components/dialog",
    related = listOf("bottom-sheet", "snackbar"),
    examples = listOf(
        CodeExample(
            title = "AlertDialog",
            description = "A structured confirmation with confirm and dismiss buttons.",
            code = """
                var open by remember { mutableStateOf(false) }
                Button(onClick = { open = true }) { Text("Delete") }
                if (open) {
                    AlertDialog(
                        onDismissRequest = { open = false },
                        icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
                        title = { Text("Delete item?") },
                        text = { Text("This action cannot be undone.") },
                        confirmButton = { TextButton(onClick = { open = false }) { Text("Delete") } },
                        dismissButton = { TextButton(onClick = { open = false }) { Text("Cancel") } }
                    )
                }
            """,
            demo = { AlertDialogDemo() }
        ),
        CodeExample(
            title = "Custom dialog",
            description = "Wrap any content — here a Card — in the low-level Dialog.",
            code = """
                var open by remember { mutableStateOf(false) }
                Button(onClick = { open = true }) { Text("Show card") }
                if (open) {
                    Dialog(onDismissRequest = { open = false }) {
                        Card {
                            Column(Modifier.padding(24.dp)) {
                                Text("Custom content")
                                TextButton(onClick = { open = false }) { Text("Close") }
                            }
                        }
                    }
                }
            """,
            demo = { CustomDialogDemo() }
        )
    )
)

private val tooltipComponent = Component(
    id = "tooltip",
    name = "Tooltip",
    group = ComponentGroup.Communication,
    summary = "Plain tooltips anchored to an interactive element",
    description = "Tooltips reveal a brief label for an element on long-press or hover. A " +
        "TooltipBox positions a PlainTooltip relative to its anchor and is driven by a " +
        "TooltipState that controls when the tooltip appears.",
    docUrl = "https://developer.android.com/jetpack/compose/components/tooltip",
    related = listOf("icon-button", "badge"),
    examples = listOf(
        CodeExample(
            title = "Plain tooltip",
            description = "Anchor a PlainTooltip to an IconButton via TooltipBox.",
            code = """
                val state = rememberTooltipState()
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    tooltip = { PlainTooltip { Text("More info") } },
                    state = state
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Info, contentDescription = "Info")
                    }
                }
            """,
            demo = { PlainTooltipDemo() }
        )
    )
)

// MARK: - Stateful demos

@Composable
private fun BadgeCounter() {
    var count by remember { mutableIntStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BadgedBox(badge = { if (count > 0) Badge { Text("$count") } }) {
            Icon(Icons.Filled.Email, contentDescription = "Mail")
        }
        Button(onClick = { count++ }) { Text("Add") }
        OutlinedButton(onClick = { count = 0 }) { Text("Clear") }
    }
}

@Composable
private fun DeterminateProgress() {
    var target by remember { mutableStateOf(0f) }
    val progress by animateFloatAsState(targetValue = target, label = "progress")
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
        Text("${(progress * 100).toInt()}%")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { target = (target + 0.2f).coerceAtMost(1f) }) { Text("Advance") }
            OutlinedButton(onClick = { target = 0f }) { Text("Reset") }
        }
    }
}

@Composable
private fun SimpleSnackbar() {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxWidth().height(120.dp)) {
        Button(
            onClick = { scope.launch { hostState.showSnackbar("Message sent") } },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text("Send")
        }
        SnackbarHost(hostState = hostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun ActionSnackbar() {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var lastResult by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        val result = hostState.showSnackbar(
                            message = "Item deleted",
                            actionLabel = "Undo"
                        )
                        lastResult = if (result == SnackbarResult.ActionPerformed) {
                            "Undo tapped"
                        } else {
                            "Dismissed"
                        }
                    }
                }
            ) {
                Text("Delete")
            }
            if (lastResult.isNotEmpty()) {
                Text(lastResult)
            }
        }
        SnackbarHost(hostState = hostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun AlertDialogDemo() {
    var open by remember { mutableStateOf(false) }
    Button(onClick = { open = true }) { Text("Delete") }
    if (open) {
        AlertDialog(
            onDismissRequest = { open = false },
            icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
            title = { Text("Delete item?") },
            text = { Text("This action cannot be undone.") },
            confirmButton = { TextButton(onClick = { open = false }) { Text("Delete") } },
            dismissButton = { TextButton(onClick = { open = false }) { Text("Cancel") } }
        )
    }
}

@Composable
private fun CustomDialogDemo() {
    var open by remember { mutableStateOf(false) }
    Button(onClick = { open = true }) { Text("Show card") }
    if (open) {
        Dialog(onDismissRequest = { open = false }) {
            Card {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Custom content")
                    Text(
                        text = "This dialog hosts any composable you like.",
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { open = false }) { Text("Close") }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlainTooltipDemo() {
    val state = rememberTooltipState()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = { PlainTooltip { Text("More info") } },
        state = state
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Info, contentDescription = "Info")
        }
    }
}
