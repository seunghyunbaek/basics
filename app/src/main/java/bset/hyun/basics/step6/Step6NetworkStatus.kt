package bset.hyun.basics.step6

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

enum class STEP6_NETWORK_STATUS {
    TYPE_WIFI,
    TYPE_MOBILE,
    TYPE_NOT_CONNECTED
}
class Step6NetworkStatus {
    companion object {

        fun getConnectivityStatus(context: Context) : STEP6_NETWORK_STATUS {
            val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager // SystemService가 리턴됩니다.

            var networkInfo = manager.activeNetworkInfo

            if(networkInfo != null) {
                val type = networkInfo.type
                if(type == ConnectivityManager.TYPE_MOBILE) { // 3G, LTE
                    return STEP6_NETWORK_STATUS.TYPE_MOBILE
                } else if (type == ConnectivityManager.TYPE_WIFI) {
                    return STEP6_NETWORK_STATUS.TYPE_WIFI
                }
            }

            return STEP6_NETWORK_STATUS.TYPE_NOT_CONNECTED
        }

        fun getConnectivityStatus2(context: Context): STEP6_NETWORK_STATUS {
            val manager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager // SystemService가 리턴됩니다.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (manager != null) {
                    val networkCapabilities= manager.getNetworkCapabilities(manager.activeNetwork)

                    if(networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return STEP6_NETWORK_STATUS.TYPE_WIFI
                        } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return STEP6_NETWORK_STATUS.TYPE_MOBILE
                        }
                    }
                }

                return STEP6_NETWORK_STATUS.TYPE_NOT_CONNECTED
            } else {
                return STEP6_NETWORK_STATUS.TYPE_NOT_CONNECTED
            }
        }
    }
}