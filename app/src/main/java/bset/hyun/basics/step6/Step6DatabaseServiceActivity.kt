package bset.hyun.basics.step6

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step6_database_service.*

class Step6DatabaseServiceActivity : AppCompatActivity() {

    val serviceIntent: Intent by lazy { Intent(applicationContext, Step6DatabaseService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step6_database_service)


        step6DatabaseServiceBtn1.setOnClickListener {
            startService(serviceIntent)
        }

        step6DatabaseServiceBtn2.setOnClickListener {
            stopService(serviceIntent)
        }

        step6DatabaseServiceBtn3.setOnClickListener {
            val intent = Intent(applicationContext, Step6DatabaseService::class.java)
            intent.putExtra("commandnumber", 1)
            startService(intent)
        }

        val passedIntent= intent
        processCommand(passedIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        processCommand(intent)

        super.onNewIntent(intent)
    }


    fun processCommand(intent: Intent?) {
        if (intent != null) {
            val cNum = intent.getIntExtra("commandnumber", -1)
            val data = intent.getStringExtra("data")

            if(cNum == 1) {
                Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
