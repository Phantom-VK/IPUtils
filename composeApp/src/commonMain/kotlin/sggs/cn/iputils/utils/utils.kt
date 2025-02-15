package sggs.cn.iputils.utils

class IPCalculator(){

    fun analyzeIP(ip: String): IPInfo {
        validateIP(ip)

        val ipClass = getClass(ip)
        val defaultMask = getSubnetMask(ip)
        val networkID = getNetworkID(ip)
        val binaryIP = ipToBinary(ip)
        val binaryMask = ipToBinary(defaultMask)
        val addressType = getAddressType(ip)
        val isGoodForHost = isGoodForHosting(ip)
        val numberOfHosts = getNumberOfHosts(defaultMask)
        val broadcastAddress = getBroadcastAddress(networkID, defaultMask)
        val (firstUsable, lastUsable) = getUsableIPRange(networkID, broadcastAddress)

        return IPInfo(
            address = ip,
            ipClass = ipClass,
            subnetMask = defaultMask,
            networkID = networkID,
            binaryAddress = binaryIP,
            binaryMask = binaryMask,
            addressType = addressType,
            isGoodForHost = isGoodForHost,
            numberOfSubnets = 0, // For default mask
            numberOfHosts = numberOfHosts,
            broadcastAddress = broadcastAddress,
            firstUsableIP = firstUsable,
            lastUsableIP = lastUsable
        )
    }

    private fun validateIP(ip: String) {
        val octets = ip.split(".")
        if (octets.size != 4) throw IllegalArgumentException("IP must have 4 octets")

        for (octet in octets) {
            val num = octet.toIntOrNull()
                ?: throw IllegalArgumentException("Each octet must be a number")
            if (num < 0 || num > 255)
                throw IllegalArgumentException("Each octet must be between 0 and 255")
        }
    }
    private fun getFIrstOctet(ip:String) = ip.split(".")[0].toInt()

    private fun getSubnetMask(ip :String) : String{

        return when(getFIrstOctet(ip)){
            in 0..127 -> "255.0.0.0"
            in 128..191 -> "255.255.0.0"
            in 192..223 -> "255.255.255.0"
            else -> "255.255.255.255"

        }
    }

    private fun getClass(ip: String):Char {

        return when (getFIrstOctet(ip)) {
            in 0..127 -> 'A'
            in 128..191 -> 'B'
            in 192..223 -> 'C'
            in 224..239 -> 'D'
            else -> 'E'
        }
    }

    private fun ipToBinary(ip: String): String {
        val octets = ip.split(".").map { it.toInt() }
        val binaryOctets = octets.map { it.toString(2).padStart(8, '0') }
        return binaryOctets.joinToString(".")

    }

    private fun getNetworkID(ip: String): String {
        val ipOctets = ip.split(".").map { it.toInt() }
        val maskOctets = getSubnetMask(ip).split(".").map { it.toInt() }

        val networkID = ipOctets.zip(maskOctets) { ipPart, maskPart ->
            ipPart and maskPart
        }

        return networkID.joinToString(".")
    }

    private fun getAddressType(ip: String): String {
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
    private fun isGoodForHosting(ip: String): Boolean {
        val lastOctet = ip.split(".")[3].toInt()
        val type = getAddressType(ip)

        return type == "Public" && lastOctet != 0 && lastOctet != 255
    }

    private fun getNumberOfHosts(subnetMask: String): Int {
        val binaryMask = ipToBinary(subnetMask).replace(".", "")
        val hostBits = binaryMask.count { it == '0' }

        return (1 shl hostBits) - 2
    }

    private fun getBroadcastAddress(ip: String, subnetMask: String): String {
        val ipOctets = ip.split(".").map { it.toInt() }
        val maskOctets = subnetMask.split(".").map { it.toInt() }

        val broadcastOctets = ipOctets.zip(maskOctets) { ipPart, maskPart ->
            ipPart or maskPart.inv() and 0xFF
        }

        return broadcastOctets.joinToString(".")
    }
    private fun getUsableIPRange(networkID: String, broadcastID: String): Pair<String, String> {
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
}

data class IPInfo(
    val address: String,
    val ipClass: Char,
    val subnetMask: String,
    val networkID: String,
    val binaryAddress: String,
    val binaryMask: String,
    val addressType: String,
    val isGoodForHost: Boolean,
    val numberOfSubnets: Int,
    val numberOfHosts: Int,
    val broadcastAddress: String,
    val firstUsableIP: String,
    val lastUsableIP: String
)