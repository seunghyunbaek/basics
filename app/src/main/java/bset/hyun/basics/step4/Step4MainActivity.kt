package bset.hyun.basics.step4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step4_main.*

class Step4MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_main)

        step4Btn1.setOnClickListener {
            val intent = Intent(this, Step4FragmentActivity::class.java)
            startActivity(intent)
        }

        step4Btn2.setOnClickListener {
            val intent = Intent(this, Step4Fragment2Activity::class.java)
            startActivity(intent)
        }

        step4Btn3.setOnClickListener {
            val intent = Intent(this, Step4OptionMenuActivity::class.java)
            startActivity(intent)
        }
    }
}
