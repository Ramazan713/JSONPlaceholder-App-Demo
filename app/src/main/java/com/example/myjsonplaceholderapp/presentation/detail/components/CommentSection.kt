package com.example.myjsonplaceholderapp.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjsonplaceholderapp.domain.models.Comment
import com.example.myjsonplaceholderapp.presentation.utils.SampleData

@Composable
fun CommentSection(
    modifier: Modifier = Modifier,
    comments: List<Comment>,
    onDeleteComment: (Comment) -> Unit,
    title: String = "Comments"
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        comments.forEach { comment->
            CommentRow(
                comment = comment,
                onDelete = {
                    onDeleteComment(comment)
                }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CommentSectionPreview() {
    CommentSection(
        comments = SampleData.postComments.comments,
        onDeleteComment = {}
    )
}