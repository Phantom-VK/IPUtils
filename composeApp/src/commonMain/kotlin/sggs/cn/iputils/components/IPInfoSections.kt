package sggs.cn.iputils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun IPInformation(
    ipAddress: String,
    subnetMask: String,
    networkId: String,
    onReset: () -> Unit,
    onDefaultMask: () -> Unit,
    onComputeNow: () -> Unit,
    onIpChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "- IP Information -",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "IP Address:",
                modifier = Modifier.width(90.dp)
            )
            OutlinedTextField(
                value = ipAddress,
                onValueChange = onIpChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IPButton(
                text = "Reset",
                onClick = onReset,
                modifier = Modifier.width(100.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Subnet Mask:",
                modifier = Modifier.width(90.dp)
            )
            OutlinedTextField(
                value = subnetMask,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IPButton(
                text = "Default Mask",
                modifier = Modifier.width(100.dp),
                onClick = onDefaultMask
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Network ID:",
                modifier = Modifier.width(90.dp)
            )
            OutlinedTextField(
                value = networkId,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IPButton(
                onClick = onComputeNow,
                text = "Compute Now",
                modifier = Modifier.width(100.dp),
            )
        }
    }
}

@Composable
fun NetworkInformation(
    ipClass: Char,
    addressType: String,
    isGoodForHost: Boolean,
    reason: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "- Network Information -",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "IP Address Class:",
                modifier = Modifier.width(120.dp)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ipClass.toString(),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Address Type:",
                modifier = Modifier.width(120.dp)
            )
            OutlinedTextField(
                value = addressType,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Yes/No")
                Text("Reason")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Good IP For Host:",
                    modifier = Modifier.width(120.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.LightGray, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isGoodForHost) "Yes" else "No",
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = reason,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun BinaryInformation(
    binaryIP: String,
    binaryMask: String,
    binaryNetworkId: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "- Binary Information -",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "IP Address:",
                modifier = Modifier.width(100.dp)
            )
            OutlinedTextField(
                value = binaryIP,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Mask:",
                modifier = Modifier.width(100.dp)
            )
            OutlinedTextField(
                value = binaryMask,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Network ID:",
                modifier = Modifier.width(100.dp)
            )
            OutlinedTextField(
                value = binaryNetworkId,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }
    }
}
@Composable
fun SubnettingInformation(
    numberOfSubnetworks: Int,
    numberOfHosts: Int,
    range: String,
    networkIDs: List<String>,
    broadcastIDs: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "- Subnetting Information -",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "# of Subnetworks:",
                modifier = Modifier.width(140.dp)
            )
            OutlinedTextField(
                value = numberOfSubnetworks.toString(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.width(80.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "# of Hosts:",
                modifier = Modifier.width(140.dp)
            )
            OutlinedTextField(
                value = numberOfHosts.toString(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.width(80.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = "Range:",
                modifier = Modifier.width(140.dp)
            )
            OutlinedTextField(
                value = range,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.width(80.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                )
            )
        }

        Row(
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        ) {
            Text(
                text = "Network ID's",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Broadcast ID's",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
        }

        // List of Network IDs and Broadcast IDs
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.LightGray, RoundedCornerShape(4.dp))
                .padding(4.dp)
        ) {
            Row {
                // This would be replaced with a proper list/scroll implementation
                Column(modifier = Modifier.weight(1f)) {
                    for (id in networkIDs) {
                        Text(id)
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    for (id in broadcastIDs) {
                        Text(id)
                    }
                }
            }
        }
    }
}