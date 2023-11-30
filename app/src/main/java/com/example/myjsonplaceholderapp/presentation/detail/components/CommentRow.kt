package com.example.myjsonplaceholderapp.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjsonplaceholderapp.domain.models.Comment
import com.example.myjsonplaceholderapp.presentation.utils.SampleData

@Composable
fun CommentRow(
    modifier: Modifier = Modifier,
    comment: Comment,
    onDelete: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = comment.body,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.DeleteForever,"delete")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CommentRowPreview() {
    CommentRow(
        comment = SampleData.comment,
        onDelete = {}
    )
}