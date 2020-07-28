package bset.hyun.basics.step6

import android.app.Service
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.IBinder
import android.util.Log

class Step6DatabaseService : Service() {

    var database: SQLiteDatabase? = null

    override fun onCreate() {
        Log.d("서비스", "서비스 시작됨")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d("서비스", "서비스 멈춤")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("서비스", "서비스 커맨드")

        if (intent == null) {
            return Service.START_STICKY // 서비스가 종료되었어도 다시 자동으로 실행해달라는 의미
        } else {
            processCommand(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun processCommand(intent: Intent) {
        val command = intent.getStringExtra("command")
        val cNum = intent.getIntExtra("commandnumber", -1)

        if (cNum == -1) {
            return
        } else if (cNum == 1) { // 텍스트 뷰
            val showIntent = Intent(applicationContext, Step6DatabaseServiceActivity::class.java)
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP)

            showIntent.putExtra("commandnumber", 1)
            showIntent.putExtra("data", "텍스트 테스트")
            startActivity(showIntent)
        } else if (cNum == 2) {
            Log.d("서비스", "processCommand(2)")
            openDatabaseHelper("customer.db")
        } else if (cNum == 3) {
            Log.d("서비스", "processCommand(3)")
            val tableName = "customer"
            createTable(tableName)
        } else if (cNum == 4) {
            Log.d("서비스", "processCommand(4)")
            selectData()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    fun openDatabaseHelper(databaseName: String) {
        val helper = Step6DatabaseHelper(applicationContext, databaseName, null, 3)
        database = helper.writableDatabase
    }

    fun createTable(tableName: String) {
        database?.run {
            val sql = "create table if not exists ${tableName} (_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)"
            this.execSQL(sql)
            Log.d("서비스", "테이블 생성")
        }
    }

    fun selectData() {
        database?.run {
            val tableName = "customer"
            val sql = "select * from ${tableName}"
            val cursor = this.rawQuery(sql, null)

            Log.d("서비스", "${cursor.count}")

            var list = ArrayList<String>()
            for(i in 0 until cursor.count) {
                cursor.moveToNext()
                val data = "id : ${cursor.getInt(0)}, name : ${cursor.getString(1)}, age : ${cursor.getInt(2)}, mobile : ${cursor.getString(3)}"
//                Log.d("서비스", "id : ${cursor.getInt(0)}, name : ${cursor.getString(1)}, age : ${cursor.getInt(2)}, mobile : ${cursor.getString(3)}")
                Log.d("서비스", "$data")
                list.add(data)
            }
            cursor.close()

            val showIntent = Intent(applicationContext, Step6DatabaseServiceActivity::class.java)
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            showIntent.putExtra("commandnumber", 4)
            showIntent.putStringArrayListExtra("data", list)
            startActivity(showIntent)
        }
    }

    inner class Step6DatabaseHelper : SQLiteOpenHelper {

        constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version) {}

        override fun onCreate(db: SQLiteDatabase?) {
//            if (db!= null) {
            Log.d("서비스", "onCreate() 호출됨")

                val tableName = "customer"
                val sql = "create table if not exists ${tableName} (_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)"
                db!!.execSQL(sql)
//            }
            Log.d("서비스", "테이블 생성")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}
