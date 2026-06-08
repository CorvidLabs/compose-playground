package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.TextInputs] group: typography and text entry.
internal val textComponents: List<Component> get() = listOf(
    textComponent,
    textFieldComponent
)

private val textComponent = Component(
    id = "text",
    name = "Text & Typography",
    group = ComponentGroup.TextInputs,
    summary = "Type scale, annotated spans, overflow, and selection",
    description = "Text renders strings using the Material 3 type scale. It supports rich, " +
        "inline styling via AnnotatedString, truncation with TextOverflow, and user text " +
        "selection through SelectionContainer.",
    docUrl = "https://developer.android.com/jetpack/compose/text",
    related = listOf("text-field"),
    examples = listOf(
        CodeExample(
            title = "Type scale",
            description = "The Material 3 type scale ranges from display down to label.",
            code = """
                Text("Display", style = MaterialTheme.typography.displaySmall)
                Text("Headline", style = MaterialTheme.typography.headlineSmall)
                Text("Title", style = MaterialTheme.typography.titleMedium)
                Text("Body", style = MaterialTheme.typography.bodyMedium)
                Text("Label", style = MaterialTheme.typography.labelMedium)
            """,
            demo = { TypeScaleDemo() }
        ),
        CodeExample(
            title = "Annotated spans",
            description = "buildAnnotatedString applies bold, italic, underline, and color spans.",
            code = """
                Text(buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Bold ") }
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("italic ") }
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("underline ") }
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) { append("color") }
                })
            """,
            demo = { AnnotatedDemo() }
        ),
        CodeExample(
            title = "Overflow",
            description = "Limit lines and truncate the remainder with an ellipsis.",
            code = """
                Text(
                    text = longText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            """,
            demo = { OverflowDemo() }
        ),
        CodeExample(
            title = "Selection",
            description = "Wrap text in a SelectionContainer to make it user-selectable.",
            code = """
                SelectionContainer {
                    Text("Press and hold to select and copy this text.")
                }
            """,
            demo = { SelectionDemo() }
        )
    )
)

private val textFieldComponent = Component(
    id = "text-field",
    name = "Text Field",
    group = ComponentGroup.TextInputs,
    summary = "Filled, outlined, error, password, search, and multi-line entry",
    description = "Text fields let people enter and edit text. Material 3 offers filled and " +
        "outlined styles with labels, placeholders, supporting text, error states, leading " +
        "and trailing icons, keyboard options, and visual transformations.",
    docUrl = "https://developer.android.com/jetpack/compose/text/user-input",
    related = listOf("text", "chip"),
    examples = listOf(
        CodeExample(
            title = "Filled",
            description = "A filled field with label, placeholder, and a Clear action when filled.",
            code = """
                var text by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Name") },
                    placeholder = { Text("Enter your name") },
                    singleLine = true,
                    trailingIcon = {
                        if (text.isNotEmpty()) {
                            IconButton(onClick = { text = "" }) {
                                Icon(Icons.Filled.Clear, "Clear")
                            }
                        }
                    }
                )
            """,
            demo = { FilledFieldDemo() }
        ),
        CodeExample(
            title = "Error & validation",
            description = "An outlined field that flags an invalid email via isError and supportingText.",
            code = """
                var email by rememberSaveable { mutableStateOf("") }
                val isError = email.isNotEmpty() && !email.contains("@")
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    isError = isError,
                    supportingText = {
                        Text(if (isError) "Must contain @" else "We'll never share it")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )
            """,
            demo = { EmailFieldDemo() }
        ),
        CodeExample(
            title = "Password",
            description = "Toggle a visual transformation to show or hide the entry.",
            code = """
                var password by rememberSaveable { mutableStateOf("") }
                var visible by rememberSaveable { mutableStateOf(false) }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (visible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { visible = !visible }) {
                            Icon(
                                if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                if (visible) "Hide password" else "Show password"
                            )
                        }
                    }
                )
            """,
            demo = { PasswordFieldDemo() }
        ),
        CodeExample(
            title = "Search",
            description = "A leading Search icon hints at the field's purpose.",
            code = """
                var query by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Search") },
                    leadingIcon = { Icon(Icons.Filled.Search, null) },
                    singleLine = true
                )
            """,
            demo = { SearchFieldDemo() }
        ),
        CodeExample(
            title = "Multi-line",
            description = "Set minLines to reserve room for several lines of input.",
            code = """
                var notes by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    minLines = 3
                )
            """,
            demo = { MultiLineFieldDemo() }
        )
    )
)

// MARK: - Text demos

@Composable
private fun TypeScaleDemo() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Display", style = androidx.compose.material3.MaterialTheme.typography.displaySmall)
        Text("Headline", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
        Text("Title", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text("Body", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
        Text("Label", style = androidx.compose.material3.MaterialTheme.typography.labelMedium)
    }
}

@Composable
private fun AnnotatedDemo() {
    val annotated = buildAnnotatedString {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Bold ") }
        withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("italic ") }
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("underline ") }
        withStyle(
            SpanStyle(color = androidx.compose.material3.MaterialTheme.colorScheme.primary)
        ) {
            append("color")
        }
    }
    Text(annotated)
}

@Composable
private fun OverflowDemo() {
    Text(
        text = "This passage is intentionally long so that it overflows the two-line limit and " +
            "gets truncated with an ellipsis to keep the layout tidy and predictable.",
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun SelectionDemo() {
    SelectionContainer {
        Text("Press and hold to select and copy this text.")
    }
}

// MARK: - Text field demos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilledFieldDemo() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Name") },
        placeholder = { Text("Enter your name") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailFieldDemo() {
    var email by rememberSaveable { mutableStateOf("") }
    val isError = email.isNotEmpty() && !email.contains("@")
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        isError = isError,
        supportingText = {
            Text(if (isError) "Must contain @" else "We'll never share it")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordFieldDemo() {
    var password by rememberSaveable { mutableStateOf("") }
    var visible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (visible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    imageVector = if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = if (visible) "Hide password" else "Show password"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchFieldDemo() {
    var query by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MultiLineFieldDemo() {
    var notes by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = notes,
        onValueChange = { notes = it },
        label = { Text("Notes") },
        minLines = 3,
        modifier = Modifier.fillMaxWidth()
    )
}
