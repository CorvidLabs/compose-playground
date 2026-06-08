package com.corvidlabs.composeplayground.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample

/// Renders one [CodeExample]: an optional title/description, the live interactive demo,
/// then a collapsible code panel. This is the repeating unit on a component detail page.
@Composable
internal fun ExampleCard(
    example: CodeExample,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (example.title.isNotEmpty()) {
                Text(example.title, style = MaterialTheme.typography.titleSmall)
            }
            if (example.description.isNotEmpty()) {
                Text(
                    example.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            example.demo()
            CodeBlock(code = example.code)
        }
    }
}
