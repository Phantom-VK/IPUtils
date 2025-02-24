package sggs.cn.iputils.utils

// IPConverterUtils.kt
import kotlin.math.pow

fun convertDotToLong(dotIp: String): Long {
    val parts = dotIp.split(".")
    if (parts.size != 4) throw IllegalArgumentException("Invalid IP address format")

    var result = 0L
    for (i in parts.indices) {
        val part = parts[i].toInt()
        if (part !in 0..255) throw IllegalArgumentException("Invalid IP address segment")
        result = result or (part.toLong() shl (24 - (8 * i)))
    }
    return result
}

fun convertLongToDot(longIp: Long): String {
    return buildString {
        append((longIp shr 24) and 0xFF)
        append(".")
        append((longIp shr 16) and 0xFF)
        append(".")
        append((longIp shr 8) and 0xFF)
        append(".")
        append(longIp and 0xFF)
    }
}

fun toBinaryString(value: Long): String {
    val maskedValue = value and 0xffffffffL
    return maskedValue.toString(2).padStart(32, '0')
}




fun getNetworkClass(ipAddress: String): String {
    val firstOctet = ipAddress.split(".")[0].toInt()
    return when {
        firstOctet in 1..126 -> "A"
        firstOctet in 128..191 -> "B"
        firstOctet in 192..223 -> "C"
        firstOctet in 224..239 -> "D"
        firstOctet in 240..255 -> "E"
        else -> "Invalid"
    }
}

fun getNetworkNumber(ipAddress: String): Int {
    val firstOctet = ipAddress.split(".")[0].toInt()
    return firstOctet and 127  // 127 = 0x7F
}

fun getLocalHostNumber(ipAddress: String): Int {
    val parts = ipAddress.split(".")
    var result = 0
    for (i in 1..3) {
        result = (result shl 8) or parts[i].toInt()
    }
    return result
}

fun calculateNumberOfHosts(cidr: Int): Long {
    return 2.0.pow(32 - cidr).toLong() - 2
}
