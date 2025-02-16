package sggs.cn.iputils.webScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sggs.cn.iputils.components.BinaryInformation
import sggs.cn.iputils.components.IPInformation
import sggs.cn.iputils.components.NetworkInformation
import sggs.cn.iputils.components.SubnettingInformation
import sggs.cn.iputils.utils.IPInfo

@Composable
fun IPCalculatorScreen() {
    var ipAddress by remember { mutableStateOf("10.70.6.1") }
    var subnetMask by remember { mutableStateOf("255.255.255.0") }
    var networkId by remember { mutableStateOf("10.70.6.0/24") }

    // This would typically be calculated from your IPCalculator class
    val ipInfo = remember {
        IPInfo(
            address = ipAddress,
            ipClass = 'A',
            subnetMask = subnetMask,
            networkID = networkId,
            binaryAddress = "00001010.01000110.00000110.00000001",
            binaryMask = "11111111.11111111.11111111.00000000",
            addressType = "Reserved",
            isGoodForHost = true,
            numberOfSubnets = 0,
            numberOfHosts = 254,
            broadcastAddress = "10.70.6.255",
            firstUsableIP = "10.70.6.1",
            lastUsableIP = "10.70.6.254"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "IP Calculator",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Left Column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                IPInformation(
                    ipAddress = ipAddress,
                    subnetMask = subnetMask,
                    networkId = networkId,
                    onReset = { ipAddress = "" },
                    onDefaultMask = { /* handle default mask */ },
                    onComputeNow = { /* handle compute */ },
                    onIpChange = { ipAddress = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                BinaryInformation(
                    binaryIP = ipInfo.binaryAddress,
                    binaryMask = ipInfo.binaryMask,
                    binaryNetworkId = "00001010.01000110.00000110.00000000",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right Column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                NetworkInformation(
                    ipClass = ipInfo.ipClass,
                    addressType = ipInfo.addressType,
                    isGoodForHost = ipInfo.isGoodForHost,
                    reason = "",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                SubnettingInformation(
                    numberOfSubnetworks = 0,
                    numberOfHosts = ipInfo.numberOfHosts,
                    range = "1",
                    networkIDs = listOf("10.70.6.0"),
                    broadcastIDs = listOf("10.70.6.255"),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { /* handle save */ },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green
                )
            ) {
                Text("Calculate")
            }

            Button(
                onClick = { /* handle save */ },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
            ) {
                Text("Save")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* handle close */ },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
            ) {
                Text("Close")
            }
        }
    }
}