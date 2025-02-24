import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import sggs.cn.iputils.utils.*

@Composable
fun IPConverterScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var dotIpAddress by remember { mutableStateOf("") }
    var longIpAddress by remember { mutableStateOf("") }

    // Binary
    var binaryDotIp by remember { mutableStateOf("") }
    var binaryLongIp by remember { mutableStateOf("") }

    // Network info
    var networkClass by remember { mutableStateOf("") }
    var networkNumber by remember { mutableStateOf("") }
    var localHostNumber by remember { mutableStateOf("") }

    // This function updates all fields when Dot IP changes
    fun updateFromDotIp(ip: String) {
        try {
            val longValue = convertDotToLong(ip)
            dotIpAddress = ip
            longIpAddress = longValue.toString()

            // Update Binary
            binaryDotIp = toBinaryString(longValue)
            binaryLongIp = toBinaryString(longValue)  // Same as Dot IP, but we'll keep separate variables in UI

            // Update Network Info
            val netClass = getNetworkClass(ip)
            val netNumber = getNetworkNumber(ip)
            val localNumber = getLocalHostNumber(ip)

            networkClass = netClass
            networkNumber = netNumber.toString()
            localHostNumber = localNumber.toString()

        } catch (e: Exception) {
            // If user typed an invalid IP, we can handle the error or clear fields
            longIpAddress = ""
            binaryDotIp = ""
            binaryLongIp = ""
            networkClass = ""
            networkNumber = ""
            localHostNumber = ""
        }
    }

    // This function updates all fields when Long IP changes
    fun updateFromLongIp(longStr: String) {
        try {
            val longValue = longStr.toLong()
            val dotIp = convertLongToDot(longValue)
            dotIpAddress = dotIp
            longIpAddress = longStr

            // Update Binary
            binaryDotIp = toBinaryString(longValue)
            binaryLongIp = toBinaryString(longValue)

            // Update Network Info
            val netClass = getNetworkClass(dotIp)
            val netNumber = getNetworkNumber(dotIp)
            val localNumber = getLocalHostNumber(dotIp)

            networkClass = netClass
            networkNumber = netNumber.toString()
            localHostNumber = localNumber.toString()

        } catch (e: Exception) {
            // If user typed an invalid Long IP
            dotIpAddress = ""
            binaryDotIp = ""
            binaryLongIp = ""
            networkClass = ""
            networkNumber = ""
            localHostNumber = ""
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // IP Information Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "- IP Information -",
                        style = MaterialTheme.typography.h6,
                        fontStyle = FontStyle.Italic
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextField(
                            value = dotIpAddress,
                            onValueChange = {
                                // Update the UI from the dot IP
                                updateFromDotIp(it)
                            },
                            label = { Text("Dot IP Address") },
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = {
                                updateFromDotIp(dotIpAddress)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Dot IP To Long")
                        }
                    }

                    Text(
                        text = "separated by dots ('.')",
                        style = MaterialTheme.typography.caption,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextField(
                            value = longIpAddress,
                            onValueChange = {
                                updateFromLongIp(it)
                            },
                            label = { Text("Long IP Address") },
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = {
                                updateFromLongIp(longIpAddress)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Long To Dot IP")
                        }
                    }
                }
            }

            // Binary Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "- Binary -",
                        style = MaterialTheme.typography.h6,
                        fontStyle = FontStyle.Italic
                    )

                    Text("- Dot IP -")
                    OutlinedTextField(
                        value = binaryDotIp,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("- Long IP -")
                    OutlinedTextField(
                        value = binaryLongIp,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Network Information Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "- Network Information -",
                        style = MaterialTheme.typography.h6,
                        fontStyle = FontStyle.Italic
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedTextField(
                            value = networkClass,
                            onValueChange = {},
                            label = { Text("Network Class") },
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = networkNumber,
                            onValueChange = {},
                            label = { Text("Network Of/127") },
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = localHostNumber,
                            onValueChange = {},
                            label = { Text("Local Host Number Of/16,777,215") },
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Button(
                onClick = onClose,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Close")
            }
        }
    }
}
