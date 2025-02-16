package sggs.cn.iputils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import sggs.cn.iputils.mobileScreens.IPCalculatorScreen
import sggs.cn.iputils.viewmodels.IPViewModel
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = IPViewModel()
        setContent {
            IPCalculatorScreen(viewModel, {
                exitProcess(0)
            })
        }
    }
}








