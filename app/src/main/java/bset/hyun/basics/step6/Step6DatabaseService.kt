package bset.hyun.basics.step6

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class Step6DatabaseService : Service() {

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
        }
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }
}
