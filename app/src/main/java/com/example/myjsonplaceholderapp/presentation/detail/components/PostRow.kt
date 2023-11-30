package com.example.myjsonplaceholderapp.presentation.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.presentation.utils.SampleData

@Composable
fun PostRow(
    modifier: Modifier = Modifier,
    post: Post,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    val shape = MaterialTheme.shapes.medium

    Card(
        shape = shape,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 1.dp, bottom = 8.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.heightIn(min = 50.dp)
            )
            Divider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = post.username.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = onUpdate,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    border = BorderStroke(1.dp,MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Text(text = "Update")
                }
                FilledTonalButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    border = BorderStroke(1.dp,MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PostRowPreview() {
    PostRow(
        post = SampleData.post,
        onUpdate = {},
        onDelete = {}
    )
}