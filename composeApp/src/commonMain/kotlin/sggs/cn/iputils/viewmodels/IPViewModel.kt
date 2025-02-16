package sggs.cn.iputils.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import sggs.cn.iputils.utils.IPCalculator
import sggs.cn.iputils.utils.IPInfo

class IPViewModel : ViewModel() {

    private val ipCalculator = IPCalculator()
    private val tempIPInfo = IPInfo(
        address = "",
        ipClass = ' ',
        subnetMask = "",
        networkID = "",
        binaryMask = "",
        binaryNetworkID = "",
        addressType = "",
        isGoodForHost = true,
        numberOfSubnets = 0,
        numberOfHosts = 0,
        broadcastAddress = "",
        firstUsableIP = "",
        lastUsableIP = "",
        binaryAddress = ""
    )

    private val _ipInfo = MutableStateFlow<IPInfo?>(tempIPInfo)
    val ipInfo: StateFlow<IPInfo?> = _ipInfo.asStateFlow()

    fun fetchIpInfo(ip: String) {
        val info = ipCalculator.analyzeIP(ip)
        _ipInfo.value = info  // Update state
    }

    fun getBinaryNetworkID(ip: String): String {
        return ipCalculator.ipToBinary(ip)

    }




}