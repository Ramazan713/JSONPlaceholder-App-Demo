package com.example.myjsonplaceholderapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjsonplaceholderapp.domain.models.User
import com.example.myjsonplaceholderapp.presentation.utils.SampleData

@Composable
fun UserRow(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit,
    margins: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
) {
    val shape = MaterialTheme.shapes.medium

    ListItem(
        modifier = modifier
            .padding(margins)
            .clip(shape)
            .clickable {
                onClick()
            }
            .padding(1.dp),
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        leadingContent = {
            Text(text = user.id.toString())
        },
        headlineContent = {
            Text(text = user.name)
        },
        supportingContent = {
            Text(text = user.email)
        },
        trailingContent = {
            Icon(imageVector = Icons.Default.NavigateNext, contentDescription = null)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun UserRowPreview() {
    UserRow(
        user = SampleData.user,
        onClick = {}
    )
}