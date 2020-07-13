package bset.hyun.basics.step5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step5_main.*



class Step5MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_main)

        step5MainBtn1.setOnClickListener {
            val intent = Intent(this, Step5ThreadActivity::class.java)
            startActivity(intent)
        }

        step5MainBtn2.setOnClickListener {
            val intent = Intent(this, Step5AsyncTaskActivity::class.java)
            startActivity(intent)
        }

        step5MainBtn3.setOnClickListener {
            val intent = Intent(this, Step5ClientActivity::class.java)
            startActivity(intent)
        }

        step5MainBtn4.setOnClickListener {
            val intent = Intent(this, Step5HttpActivity::class.java)
            startActivity(intent)
        }

        step5MainBtn6.setOnClickListener {
            val intent = Intent(this, Step5VolleyActivity::class.java)
            startActivity(intent)
        }

        step5MainBtn7.setOnClickListener {
            val intent = Intent(this, Step5JSONActivity::class.java)
            startActivity(intent)
        }

    }
}
