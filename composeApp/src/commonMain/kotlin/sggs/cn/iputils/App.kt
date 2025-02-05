package sggs.cn.iputils

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import sggs.cn.iputils.components.IPButton

@Composable
@Preview
fun App(
    platformContext: ContextFactory
){
    MaterialTheme {
        IPButton(text = "Hello There", onClick = {})
    }
}
