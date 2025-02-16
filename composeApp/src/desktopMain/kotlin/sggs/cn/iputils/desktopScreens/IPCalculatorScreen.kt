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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sggs.cn.iputils.components.BinaryInformation
import sggs.cn.iputils.components.CustomToast
import sggs.cn.iputils.components.IPButton
import sggs.cn.iputils.components.IPInformation
import sggs.cn.iputils.components.NetworkInformation
import sggs.cn.iputils.components.SubnettingInformation
import sggs.cn.iputils.utils.saveIpInfoToCsv
import sggs.cn.iputils.viewmodels.IPViewModel

@Composable
fun IPCalculatorScreen(viewModel: IPViewModel, onExit: () -> Unit) {
    var ipAddress by remember { mutableStateOf("") }
    var subnetMask by remember { mutableStateOf("") }
    var networkId by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }


    var errorMessage by remember { mutableStateOf<String?>(null) }


    val ipInfo by viewModel.ipInfo.collectAsState()

    CustomToast("Saved to CSV (Downloads Folder)!", visible = showToast) {
        showToast = false
    }


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
                        isError = errorMessage != null,
                        errorMessage = errorMessage,
                        networkId = networkId,
                        onReset = {
                            viewModel.resetInfo()
                            ipAddress = ""
                            subnetMask = ""
                            networkId = ""
                            errorMessage = null
                        },
                        onDefaultMask = { subnetMask = ip.subnetMask },
                        onComputeNow = { networkId = ip.networkID },
                        onIpChange = {
                            ipAddress = it
                            errorMessage = null
                        },
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
                        networkIDs = listOf(ip.networkID),
                        broadcastIDs = listOf(ip.broadcastAddress),
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

                IPButton(
                    text = "Calculate",
                    modifier = Modifier.weight(1f).height(44.dp)
                ) {
                    try {

                        viewModel.fetchIpInfo(ipAddress)
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = e.message ?: "Invalid IP address"
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))

                IPButton(
                    text = "Save",
                    modifier = Modifier.weight(1f).height(44.dp)
                ) {
                    ipInfo?.let { ipInfo ->
                        saveIpInfoToCsv(listOf(ipInfo))
                        showToast = true
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                IPButton(
                    text = "Close",
                    modifier = Modifier.weight(1f).height(44.dp)
                ) {
                    onExit()
                }

            }
        }
    }

}


