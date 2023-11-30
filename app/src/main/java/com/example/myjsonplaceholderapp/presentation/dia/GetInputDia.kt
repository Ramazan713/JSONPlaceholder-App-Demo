package com.example.myjsonplaceholderapp.presentation.dia

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GetInputDia(
    onDismiss: () -> Unit,
    content: String = "",
    title: String,
    onApproved: (String) -> Unit,
) {

    var text by rememberSaveable {
        mutableStateOf(content)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it},
                placeholder = {
                    Text(text = "Enter text here")
                }
            )
        },
        confirmButton = {
            Button(onClick = {
                onApproved(text)
                onDismiss()
            }) {
                Text(text = "Approve")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GetInputDiaPreview() {
    GetInputDia(
        onDismiss = {},
        onApproved = {},
        content = "",
        title = ""
    )
}