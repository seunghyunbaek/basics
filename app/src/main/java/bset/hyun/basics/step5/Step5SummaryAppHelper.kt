package bset.hyun.basics.step5

import com.android.volley.RequestQueue

class Step5SummaryAppHelper {
    companion object {
        var requestQueue: RequestQueue? = null
        val host: String = "boostcourse-appapi.connect.or.kr"
        val port: Int = 10000
    }
}