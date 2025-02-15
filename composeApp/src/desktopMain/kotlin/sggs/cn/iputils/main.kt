package sggs.cn.iputils

import IPCalculatorScreen
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "IPUtils",
    ) {
        IPCalculatorScreen()
    }
}