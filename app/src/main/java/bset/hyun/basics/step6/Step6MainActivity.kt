package bset.hyun.basics.step6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step6_main.*

class Step6MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step6_main)

        step6MainBtn1.setOnClickListener {
            val intent = Intent(this, Step6DatabaseActivity::class.java)
            startActivity(intent)
        }

        step6MainBtn2.setOnClickListener {
            val intent = Intent(this, Step6ConnectStatusActivity::class.java)
            startActivity(intent)
        }

        step6MainBtn3.setOnClickListener {
            val intent = Intent(this, Step6DatabaseServiceActivity::class.java)
            startActivity(intent)
        }

        step6MainBtn4.setOnClickListener {
            val intent = Intent(this, Step6SummaryActivity::class.java)
            startActivity(intent)
        }
    }
}
