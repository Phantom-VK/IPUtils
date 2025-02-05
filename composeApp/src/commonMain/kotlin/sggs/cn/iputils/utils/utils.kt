package sggs.cn.iputils.utils

fun getFIrstOctet(ip:String) = ip.split(".")[0].toInt()

fun getSubnetMask(ip :String) : String{

    return when(getFIrstOctet(ip)){
        in 0..127 -> "255.0.0.0"
        in 128..191 -> "255.255.0.0"
        in 192..223 -> "255.255.255.0"
        else -> "NO SUBNET MASK"

    }
}

fun getClass(ip: String):Char {

    return when (getFIrstOctet(ip)) {
        in 0..127 -> 'A'
        in 128..191 -> 'B'
        in 192..223 -> 'C'
        in 224..239 -> 'D'
        else -> 'E'
    }
}

fun ipToBinary(ip: String): String {
    val octets = ip.split(".").map { it.toInt() }
    val binaryOctets = octets.map { it.toString(2).padStart(8, '0') }
    return binaryOctets.joinToString(".")

}

fun getNetworkID(ip: String): String {
    val ipOctets = ip.split(".").map { it.toInt() }
    val maskOctets = getSubnetMask(ip).split(".").map { it.toInt() }

    val networkID = ipOctets.zip(maskOctets) { ipPart, maskPart ->
        ipPart and maskPart
    }

    return networkID.joinToString(".")
}

fun getAddressType(ip: String): String {
    val firstOctet = getFIrstOctet(ip)

    return when {
        ip == "127.0.0.1" -> "Loopback"
        firstOctet in 10..10 -> "Private"
        firstOctet in 172..172 && ip.split(".")[1].toInt() in 16..31 -> "Private"
        firstOctet in 192..192 && ip.split(".")[1].toInt() == 168 -> "Private"
        firstOctet in 224..239 -> "Multicast"
        firstOctet in 240..255 -> "Experimental"
        else -> "Public"
    }
}
fun isGoodForHosting(ip: String): Boolean {
    val lastOctet = ip.split(".")[3].toInt()
    val type = getAddressType(ip)

    return type == "Public" && lastOctet != 0 && lastOctet != 255
}

fun getNumberOfHosts(subnetMask: String): Int {
    val binaryMask = ipToBinary(subnetMask).replace(".", "")
    val hostBits = binaryMask.count { it == '0' }

    return (1 shl hostBits) - 2
}

fun getBroadcastAddress(ip: String, subnetMask: String): String {
    val ipOctets = ip.split(".").map { it.toInt() }
    val maskOctets = subnetMask.split(".").map { it.toInt() }

    val broadcastOctets = ipOctets.zip(maskOctets) { ipPart, maskPart ->
        ipPart or maskPart.inv() and 0xFF
    }

    return broadcastOctets.joinToString(".")
}
fun getUsableIPRange(networkID: String, broadcastID: String): Pair<String, String> {
    val netOctets = networkID.split(".").map { it.toInt() }.toMutableList()
    val broadOctets = broadcastID.split(".").map { it.toInt() }.toMutableList()

    netOctets[3] += 1 // First usable IP
    broadOctets[3] -= 1 // Last usable IP

    return Pair(netOctets.joinToString("."), broadOctets.joinToString("."))
}
fun getNumberOfSubnets(defaultMask: String, customMask: String): Int {
    val defaultBinary = ipToBinary(defaultMask).replace(".", "").count { it == '1' }
    val customBinary = ipToBinary(customMask).replace(".", "").count { it == '1' }

    val borrowedBits = customBinary - defaultBinary
    return if (borrowedBits >= 0) 1 shl borrowedBits else 0 // 2^borrowedBits
}
