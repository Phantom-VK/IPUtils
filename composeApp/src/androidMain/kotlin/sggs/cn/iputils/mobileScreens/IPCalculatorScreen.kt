package sggs.cn.iputils.mobileScreens

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    // Request permission just once when screen loads
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (!granted) {
                Toast.makeText(context, "Storage permission is needed to save CSV files", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(WRITE_EXTERNAL_STORAGE)
    }

    CustomToast("Saved to CSV (Downloads Folder)!", visible = showToast) {
        showToast = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "IP Calculator") }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IPButton(
                    text = "Calculate",
                    modifier = Modifier.weight(1f).height(50.dp)
                ) {
                    try {
                        viewModel.fetchIpInfo(ipAddress)
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = e.message ?: "Invalid IP address or subnet mask"
                    }
                }

                IPButton(
                    text = "Save",
                    modifier = Modifier.weight(1f).height(50.dp),
                ) {
                    ipInfo?.let { ipInfo ->
                        saveIpInfoToCsv(listOf(ipInfo))
                        showToast = true
                    }
                }

                IPButton(
                    text = "Close",
                    modifier = Modifier.weight(1f).height(50.dp)
                ) {
                    onExit()
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (ipInfo != null) {
                // Mobile Layout (Column-based)
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(1) {
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
                            onDefaultMask = { ipInfo?.let { subnetMask = it.subnetMask } },
                            onComputeNow = { ipInfo?.let { networkId = it.networkID } },
                            onIpChange = {
                                ipAddress = it
                                errorMessage = null
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        ipInfo?.let { ip ->
                            BinaryInformation(
                                binaryIP = ip.binaryAddress,
                                binaryMask = ip.binaryMask,
                                binaryNetworkId = ip.binaryNetworkID,
                                modifier = Modifier.fillMaxWidth()
                            )

                            NetworkInformation(
                                ipClass = ip.ipClass,
                                addressType = ip.addressType,
                                isGoodForHost = ip.isGoodForHost,
                                reason = "",
                                modifier = Modifier.fillMaxWidth()
                            )

                            SubnettingInformation(
                                numberOfSubnetworks = ip.numberOfSubnets ?: 0,
                                numberOfHosts = ip.numberOfHosts,
                                range = "1",
                                networkIDs = listOf(ip.networkID),
                                broadcastIDs = listOf(ip.broadcastAddress),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            } else {
                // Show empty state or initial state
                Text(
                    text = "Enter an IP address and click Calculate to begin",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(vertical = 32.dp)
                )
            }
        }
    }
}