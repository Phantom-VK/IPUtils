package sggs.cn.iputils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import sggs.cn.iputils.viewmodels.IPViewModel
import sggs.cn.iputils.webScreens.IPCalculatorScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val viewModel = IPViewModel()
    ComposeViewport(document.body!!) {
        IPCalculatorScreen(viewModel = viewModel, {})
    }
}