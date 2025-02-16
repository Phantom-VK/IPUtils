package sggs.cn.iputils.desktopScreens

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
import androidx.compose.runtime.collectAsState
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
import sggs.cn.iputils.viewmodels.IPViewModel

@Composable
fun IPCalculatorScreen(viewModel: IPViewModel) {
    var ipAddress by remember { mutableStateOf("Enter Your IP") }
    var subnetMask by remember { mutableStateOf("255.255.255.255") }
    var networkId by remember { mutableStateOf("0.0.0.0") }

    // This would typically be calculated from your IPCalculator class
    val ipInfo by viewModel.ipInfo.collectAsState()


    ipInfo?.let { ip ->


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
                        onDefaultMask = { subnetMask = ip.subnetMask },
                        onComputeNow = { networkId = ip.networkID },
                        onIpChange = { ipAddress = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BinaryInformation(
                        binaryIP = ip.binaryAddress,
                        binaryMask = ip.binaryMask,
                        binaryNetworkId = ip.binaryNetworkID,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Right Column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    NetworkInformation(
                        ipClass = ip.ipClass,
                        addressType = ip.addressType,
                        isGoodForHost = ip.isGoodForHost,
                        reason = "",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SubnettingInformation(
                        numberOfSubnetworks = 0,
                        numberOfHosts = ip.numberOfHosts,
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
                    onClick = { viewModel.fetchIpInfo(ipAddress) },
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

}