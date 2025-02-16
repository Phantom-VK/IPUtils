package sggs.cn.iputils.mobileScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import sggs.cn.iputils.utils.IPCalculator
import sggs.cn.iputils.utils.IPInfo

@Composable
fun IPCalculatorScreen() {
    var ipAddress by remember { mutableStateOf("") }
    var ipInfo by remember { mutableStateOf<IPInfo?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val calculator = remember { IPCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("IP Calculator", style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = ipAddress,
            onValueChange = {
                ipAddress = it
                errorMessage = null
            },
            label = { Text("Enter IP Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null
        )

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                try {
                    ipInfo = calculator.analyzeIP(ipAddress)
                    errorMessage = null
                } catch (e: Exception) {
                    errorMessage = e.message ?: "Invalid IP address"
                    ipInfo = null
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Analyze")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ipInfo?.let { info ->
            IPInfoDisplay(info)
        }
    }
}

@Composable
fun IPInfoDisplay(ipInfo: IPInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoRow("IP Address", ipInfo.address)
            InfoRow("IP Class", ipInfo.ipClass.toString())
            InfoRow("Subnet Mask", ipInfo.subnetMask)
            InfoRow("Network ID", ipInfo.networkID)
            InfoRow("Address Type", ipInfo.addressType)
            InfoRow("Good for Host", if (ipInfo.isGoodForHost) "Yes" else "No")
            InfoRow("Number of Hosts", ipInfo.numberOfHosts.toString())

            Spacer(modifier = Modifier.height(8.dp))
            Text("Binary Information", style = MaterialTheme.typography.h6)
            InfoRow("IP (Binary)", ipInfo.binaryAddress)
            InfoRow("Mask (Binary)", ipInfo.binaryMask)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Network Range", style = MaterialTheme.typography.h6)
            InfoRow("Broadcast Address", ipInfo.broadcastAddress)
            InfoRow("First Usable IP", ipInfo.firstUsableIP)
            InfoRow("Last Usable IP", ipInfo.lastUsableIP)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.subtitle1)
        Text(value, style = MaterialTheme.typography.body1)
    }
}
