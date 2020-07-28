package best.hyun.moview.learn3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step3_sms.*

class Step3SmsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3_sms)

        lrn3_sms_btn1.setOnClickListener { finish() }

        val passedIntent = intent
        proccessCommand(passedIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        proccessCommand(intent)

        super.onNewIntent(intent)
    }

    private fun proccessCommand(intent: Intent?) {
        if (intent != null) {
            val sender = intent.getStringExtra("sender")
            val contents = intent.getStringExtra("contents")
            val date = intent.getStringExtra("date")

            lrn3_sms_edit1.setText(sender)
            lrn3_sms_edit2.setText(contents)
            lrn3_sms_edit3.setText(date)
        }
    }
}
