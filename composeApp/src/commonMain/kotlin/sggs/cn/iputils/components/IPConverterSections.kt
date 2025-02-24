package sggs.cn.iputils.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun IPInformationSection(
    dotIpAddress: String,
    longIpAddress: String,
    onDotIpChange: (String) -> Unit,
    onLongIpChange: (String) -> Unit,
    onDotToLongClick: () -> Unit,
    onLongToDotClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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

            Spacer(modifier = Modifier.height(8.dp))

            // Dot IP Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = dotIpAddress,
                    onValueChange = onDotIpChange,
                    label = { Text("Dot IP Address") },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = onDotToLongClick,
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

            // Long IP Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = longIpAddress,
                    onValueChange = onLongIpChange,
                    label = { Text("Long IP Address") },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = onLongToDotClick,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Long To Dot IP")
                }
            }

            Text(
                text = "separated by commas (',')",
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun BinarySection(
    binaryDotIp: String,
    binaryLongIp: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "- Dot IP -",
                style = MaterialTheme.typography.subtitle1
            )
            OutlinedTextField(
                value = binaryDotIp,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "MSByte To LSByte",
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "- Long IP -",
                style = MaterialTheme.typography.subtitle1
            )
            OutlinedTextField(
                value = binaryLongIp,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "MSByte To LSByte",
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun NetworkInformationSection(
    networkClass: String,
    networkNumber: String,
    localHostNumber: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Network Class
                OutlinedTextField(
                    value = networkClass,
                    onValueChange = {},
                    label = { Text("Network Class") },
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )

                // Network Number
                OutlinedTextField(
                    value = networkNumber,
                    onValueChange = {},
                    label = { Text("Network") },
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )

                // Local Host Number
                OutlinedTextField(
                    value = localHostNumber,
                    onValueChange = {},
                    label = { Text("Local Host Number") },
                    readOnly = true,
                    modifier = Modifier.weight(1.5f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Of/127",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Of/16,777,215",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.weight(1.5f)
                )
            }
        }
    }
}