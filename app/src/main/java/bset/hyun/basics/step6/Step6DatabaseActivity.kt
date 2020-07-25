package bset.hyun.basics.step6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R

class Step6DatabaseActivity : AppCompatActivity() {

    lateinit var textView: TextView
    var database: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step6_database)

        textView = findViewById(R.id.step6DatabaseText1)
        val edit = findViewById<EditText>(R.id.step6DatabaseEdit1)

        val btn = findViewById<Button>(R.id.step6DatabaseBtn1)
        btn.setOnClickListener {
            val databaseName = edit.text.toString()
            openDataBase(databaseName)
        }
    }

    fun openDataBase(databaseName: String) {
        println("openDataBase() 호출됨")

        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null)
        if (database != null) {
            println("데이터베이스 오픈됨")
        }

    }

    fun println(data: String) {
        textView.append(data+"\n")
    }
}
