package bset.hyun.basics.step6

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step6_database_service.*

class Step6DatabaseServiceActivity : AppCompatActivity() {

    val serviceIntent: Intent by lazy { Intent(applicationContext, Step6DatabaseService::class.java) }
    val textView: TextView by lazy { findViewById<TextView>(R.id.step6DatabaseServiceText1) }

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

        step6DatabaseServiceBtn4.setOnClickListener {
            val createTableIntent = Intent(applicationContext, Step6DatabaseService::class.java)
            createTableIntent.putExtra("commandnumber",3)
            startService(createTableIntent)
        }

        step6DatabaseServiceBtn5.setOnClickListener {
            val selectIntent = Intent(applicationContext, Step6DatabaseService::class.java)
            selectIntent.putExtra("commandnumber",4)
            startService(selectIntent)
        }

        val dbOpenIntent = Intent(applicationContext, Step6DatabaseService::class.java)
        dbOpenIntent.putExtra("commandnumber", 2)
        startService(dbOpenIntent)

        val passedIntent= intent
        processCommand(passedIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        processCommand(intent)

        super.onNewIntent(intent)
    }

    fun println(data: String) {
        textView.append(data + "\n")
    }

    fun processCommand(intent: Intent?) {
        println("processCommand() 실행")
        if (intent != null) {
            val cNum = intent.getIntExtra("commandnumber", -1)

            if(cNum == 1) {
                val data = intent.getStringExtra("data")
                Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
            } else if (cNum == 4) {
                println("조회된 데이터")
                val data = intent.getStringArrayListExtra("data")
                for(i in data.indices) {
                    println(data[i])
                }
            }
        }
    }
}
