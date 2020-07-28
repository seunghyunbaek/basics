package bset.hyun.basics.step6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Step6AppHelper {
    companion object {
        private val TAG = "Step6AppHelper"
        private var database: SQLiteDatabase? = null

        private val createTableOutlineSql = "create table if not exists outline " +
                "(" +
                "    _id integer PRIMARY KEY autoincrement, " +
                "    id integer, " +
                "    title text, " +
                "    title_eng text, " +
                "    dateValue text, " +
                "    user_rating float, " +
                "    audience_rating, " +
                "    reviewer_rating, " +
                "    reservation_rate, " +
                "    reservation_grade integer, " +
                "    grade integer, " +
                "    thumb text, " +
                "    image text" +
                ")"

        fun openDatabase(context: Context, databaseName: String) {

            try {
                database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null)
                println("데이터베이스 $databaseName 오픈됨")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun createTable(tableName: String) {
            println("createTable 호출됨 : $tableName")

            if (database != null) {
                if (tableName.equals("outline")) {
                    database!!.execSQL(createTableOutlineSql)
                    println("outline 테이블 생성 요청됨")
                }
            } else {
                println("데이터베이스를 먼저 오픈하세요")
            }
        }

        fun println(data: String) {
            Log.d(TAG, "$data")
        }
    }
}