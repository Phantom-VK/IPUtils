package sggs.cn.iputils.utils

import java.io.File
import java.io.FileWriter

actual fun saveIpInfoToCsv(ipInfoList: List<IPInfo>) {

    val file = File(System.getProperty("user.home") + "/Downloads/ip_info.csv")

    FileWriter(file).use { writer ->
        writer.append("Address,IP Class,Subnet Mask,Network ID,Binary Mask,Binary Network ID,Address Type,Is Good for Host,Number of Subnets,Number of Hosts,Broadcast Address,First Usable IP,Last Usable IP,Binary Address\n")

        ipInfoList.forEach { ip ->
            writer.append("${ip.address},${ip.ipClass},${ip.subnetMask},${ip.networkID},${ip.binaryMask},${ip.binaryNetworkID},${ip.addressType},${ip.isGoodForHost},${ip.numberOfSubnets},${ip.numberOfHosts},${ip.broadcastAddress},${ip.firstUsableIP},${ip.lastUsableIP},${ip.binaryAddress}\n")
        }
    }
}