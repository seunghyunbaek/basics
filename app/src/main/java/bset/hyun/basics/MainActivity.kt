package bset.hyun.basics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.step3.Step3SummaryActivity
import bset.hyun.basics.step4.Step4MainActivity
import bset.hyun.basics.step5.Step5MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        step3_main_btn.setOnClickListener{
            val intent = Intent(this, Step3SummaryActivity::class.java)
            startActivity(intent)
        }

        step4_main_btn.setOnClickListener {
            val intent = Intent(this, Step4MainActivity::class.java)
            startActivity(intent)
        }

        step5_main_btn.setOnClickListener {
            val intent = Intent(this, Step5MainActivity::class.java)
            startActivity(intent)
        }

    }
}
