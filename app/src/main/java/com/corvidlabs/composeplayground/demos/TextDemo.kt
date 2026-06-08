package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun TextDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Material type scale") {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Display Small", style = MaterialTheme.typography.displaySmall)
                Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
                Text("Title Large", style = MaterialTheme.typography.titleLarge)
                Text("Body Large — the quick brown fox.", style = MaterialTheme.typography.bodyLarge)
                Text("Label Small", style = MaterialTheme.typography.labelSmall)
            }
        }

        DemoBlock("Styled & annotated text") {
            Text(
                buildAnnotatedString {
                    append("Compose supports ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold") }
                    append(", ")
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("italic") }
                    append(", ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("underline") }
                    append(", and ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) { append("color spans") }
                    append(" in one string.")
                }
            )
        }

        DemoBlock("Overflow & max lines") {
            Text(
                text = "This is a long paragraph that will be clipped after two lines so you can see how ellipsis overflow behaves in a constrained text block within Compose.",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        DemoBlock("Selectable text") {
            SelectionContainer {
                Text("Long-press to select and copy this text.")
            }
        }
    }
}
