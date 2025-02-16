package sggs.cn.iputils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import sggs.cn.iputils.webScreens.IPCalculatorScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        IPCalculatorScreen()
    }
}