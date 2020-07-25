package bset.hyun.basics.step6

import android.content.Context
import android.database.Cursor
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

        val edit2 = findViewById<EditText>(R.id.step6DatabaseEdit2)
        val btn2 = findViewById<Button>(R.id.step6DatabaseBtn2)
        btn2.setOnClickListener {
            val tableName = edit2.text.toString()
            createTable(tableName)
        }


        val edit3 = findViewById<EditText>(R.id.step6DatabaseEdit3)
        val edit4 = findViewById<EditText>(R.id.step6DatabaseEdit4)
        val edit5 = findViewById<EditText>(R.id.step6DatabaseEdit5)
        val btn3 = findViewById<Button>(R.id.step6DatabaseBtn3)
        btn3.setOnClickListener {
            val name = edit3.text.toString().trim()
            val ageStr = edit4.text.toString().trim()
            val mobile= edit5.text.toString().trim()

            var age = -1
            try {
                age =  ageStr.toInt()
            } catch (e: Exception) { }

            insertData(name, age, mobile)
        }

        val btn4 = findViewById<Button>(R.id.step6DatabaseBtn4)
        btn4.setOnClickListener {
            selectData()
        }
    }

    fun println(data: String) {
        textView.append(data+"\n")
    }

    fun openDataBase(databaseName: String) {
        println("openDataBase() 호출됨")

        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null)
        if (database != null) {
            println("데이터베이스 오픈됨")
        }
    }

    fun createTable(tableName: String) {
        println("createTable() 호출됨")

        if(database!= null) {
            val sql: String = "create table ${tableName} (_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)"
            database!!.execSQL(sql)

            println("테이블 생성됨")
        } else {
            println("먼저 데이터베이스를 오픈하세요")
        }
    }

    fun insertData(name: String, age: Int, mobile: String) {
        println("insertData() 호출됨")

        val sql: String = "insert into customer(name, age, mobile) values(?, ?, ?)"
        val params = arrayOf(name, age, mobile)

        if (database != null) {
            database!!.execSQL(sql, params)
            println("데이터 추가함")
        } else {
            println("먼저 데이터베이스를 오픈하세요")
        }
    }

    fun selectData() {
        println("selectData() 호출됨")

        if (database != null) {
            val sql = "select name, age, mobile from customer"
            val cursor: Cursor = database!!.rawQuery(sql, null)
            println("조회된 데이터 개수: ${cursor.count}")

            for(i in 0 until cursor.count) {
                cursor.moveToNext()
                val name = cursor.getString(0) // 칼럼 인덱스를 넣어준다.
                val age = cursor.getInt(1)
                val mobile = cursor.getString(2)

                println("#$i -> $name, $age, $mobile")
            }

            // 자원이 한정되어 있기 때문에
            // cursor라고 하는 것도 실제 데이터베이스 저장소를 접근하기 때문에 웬만하면 마지막에 close를 꼭 해주셔야 합니다.
            cursor.close()
        }
    }
}
