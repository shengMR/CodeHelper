package cn.com.cys.helperlib.util

import android.content.Context
import android.net.wifi.WifiManager
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.UnknownHostException

/**
 * author: Damon
 * data: On 6/1/21
 */
object HelperWifiUtils {

    const val BROAD_IP_BOUND = "255.255.255.255"

    fun getBoundBroadcast(): InetAddress? {
        try {
            return InetAddress.getByName(BROAD_IP_BOUND)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取Wifi广播地址
     */
    fun getWiFiBroadcastAddress(context: Context): InetAddress? {
        val myWifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val myDhcpInfo = myWifiManager.dhcpInfo ?: return null
        val broadcast = (myDhcpInfo.ipAddress and myDhcpInfo.netmask or myDhcpInfo.netmask.inv())
        val quads = ByteArray(4)
        for (k in 0..3) quads[k] = (broadcast shr k * 8 and 0xFF).toByte()
        try {
            return InetAddress.getByAddress(quads)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取Wifi广播地址
     */
    fun getLocalBroadcastAddress(): InetAddress? {
        try {
            val eni = NetworkInterface.getNetworkInterfaces()
            while (eni.hasMoreElements()) {
                val networkCard = eni.nextElement()
                val ncAddrList = networkCard.interfaceAddresses
                val ncAddrIterator = ncAddrList.iterator()
                while (ncAddrIterator.hasNext()) {
                    val networkCardAddress = ncAddrIterator.next()
                    val address = networkCardAddress.address
                    if (!address.isLoopbackAddress) {
                        val hostAddress = address.hostAddress
                        return if (hostAddress.indexOf(":") > 0) {
                            // case : ipv6
                            continue
                        } else {
                            // case : ipv4
                            val broadcastAddress =
                                networkCardAddress.broadcast.hostAddress
                            try {
                                return InetAddress.getByName(broadcastAddress)
                            } catch (e: UnknownHostException) {
                                e.printStackTrace()
                            }
                            null
                        }
                    } else {
//                        String loopback = networkCardAddress.getAddress().getHostAddress();
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getLocalIpV4Address(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: java.lang.Exception) {
            return null
        }
        return null
    }

    fun getBroadcastAddress(context: Context): InetAddress? {
        var address = getWiFiBroadcastAddress(context)
        if (address == null || address.hostAddress.equals(BROAD_IP_BOUND, ignoreCase = true)) {
            address = getLocalBroadcastAddress()
            if (address == null) {
                address = getBoundBroadcast()
            }
        }
        return address
    }

}