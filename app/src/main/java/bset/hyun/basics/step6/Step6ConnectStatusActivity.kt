package bset.hyun.basics.step6

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R

class Step6ConnectStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step6_connect_status)

        val textView = findViewById<TextView>(R.id.step6ConnectStatusText1)
        val btn = findViewById<Button>(R.id.step6ConnectStatusBtn1)
        btn.setOnClickListener {
//            val status = Step6NetworkStatus.getConnectivityStatus(applicationContext)
            val status = Step6NetworkStatus.getConnectivityStatus2(applicationContext)
            if(status == STEP6_NETWORK_STATUS.TYPE_MOBILE) {
                textView.text = "모바일로 연결됨"
            } else if (status == STEP6_NETWORK_STATUS.TYPE_WIFI) {
                textView.text = "무선랜으로 연결됨"
            } else {
                textView.text = "연결 안됨"
            }
        }
    }
}
