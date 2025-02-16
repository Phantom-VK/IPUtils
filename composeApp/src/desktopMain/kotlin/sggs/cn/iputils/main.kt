package sggs.cn.iputils

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import sggs.cn.iputils.desktopScreens.IPCalculatorScreen
import sggs.cn.iputils.viewmodels.IPViewModel

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "IPUtils",
    ) {
        val viewModel = IPViewModel()
        IPCalculatorScreen(viewModel)
    }
}


