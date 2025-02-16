package sggs.cn.iputils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CustomToast(message: String, visible: Boolean, onDismiss: () -> Unit) {
    if (visible) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                backgroundColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        LaunchedEffect(Unit) {
            delay(1500)
            onDismiss()
        }
    }
}
