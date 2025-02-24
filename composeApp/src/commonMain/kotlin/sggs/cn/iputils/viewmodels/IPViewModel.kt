package sggs.cn.iputils.viewmodels

import androidx.compose.runtime.mutableStateOf
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
        binaryAddress = "",
        longFormat = 0,
        localHostNumber = 0,
        networkPortion = "",
        hostPortion = "",
        isReserved = false



    )

    private val _ipInfo = MutableStateFlow<IPInfo?>(tempIPInfo)
    val ipInfo: StateFlow<IPInfo?> = _ipInfo.asStateFlow()

    private val _dotIpAddress = mutableStateOf("")
    val dotIpAddress: String get() = _dotIpAddress.value

    private val _longIpAddress = mutableStateOf("")
    val longIpAddress: String get() = _longIpAddress.value

    private val _binaryDotIp = mutableStateOf("")
    val binaryDotIp: String get() = _binaryDotIp.value

    private val _binaryLongIp = mutableStateOf("")
    val binaryLongIp: String get() = _binaryLongIp.value

    private val _networkClass = mutableStateOf("")
    val networkClass: String get() = _networkClass.value

    private val _networkNumber = mutableStateOf("")
    val networkNumber: String get() = _networkNumber.value

    private val _localHostNumber = mutableStateOf("")
    val localHostNumber: String get() = _localHostNumber.value

    var currentScreen : String = "IP Calculator"

    fun fetchIpInfo(ip: String) {

        val info = ipCalculator.analyzeIP(ip)
        _ipInfo.value = info  // Update state
    }

    fun resetInfo(){
        _ipInfo.value = tempIPInfo

    }


}