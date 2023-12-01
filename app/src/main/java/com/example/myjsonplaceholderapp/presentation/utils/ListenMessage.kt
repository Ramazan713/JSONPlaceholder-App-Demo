package com.example.myjsonplaceholderapp.presentation.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun ListenMessage(
    message: String?,
    onClearMessage: () -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val currentOnClearMessage by rememberUpdatedState(newValue = onClearMessage)

    var toast by remember {
        mutableStateOf<Toast?>(null)
    }

    LaunchedEffect(message, lifecycleOwner.lifecycle){
        snapshotFlow { message }
            .filterNotNull()
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collectLatest { message->
                toast?.cancel()
                toast = Toast.makeText(context,message,Toast.LENGTH_LONG)
                toast?.show()
                currentOnClearMessage()
            }
    }

}