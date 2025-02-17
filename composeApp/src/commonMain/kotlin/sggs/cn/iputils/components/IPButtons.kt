package sggs.cn.iputils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IPButton(
    modifier: Modifier,
    text:String,
    onClick: () -> Unit
) {
    OutlinedButton(modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            backgroundColor = Color(0xFF333232)

        ),
        onClick = { onClick() },
    ) {
        Text(text, fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center)
    }
}




