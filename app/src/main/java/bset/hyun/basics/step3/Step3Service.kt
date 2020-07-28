package best.hyun.moview.learn3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


// 액티비티와 서비스간에 인텐트를 이용해서 서로 실행시켜줄 수가 있고, 그 다음에 실행시켜주는게 주 목적이라기보다는
// 대부분의 경우에는 데이터를 전달하는 용도로 많이 씁니다. 그래서 데이터를 전달할 때 이미 액티비티가 떠있는 경우도 감안을 해야 된다고 하는거 꼭 생각을 해야 됩니다.

class Step3Service : Service() {

    private val TAG = "Learn3Service"

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate() 호출됨")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() 호출됨")

        if(intent == null) {
            return Service.START_STICKY // STICKY는 끈적끈적하게 붙는 걸 의미하는데, 서비스가 종료됐을 때도 다시 자동으로 실행해달라고 하는 옵션과 같습니다. // 이거 관계없이 인텐트가 ㄴ
        } else {
            processCommand(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun processCommand(intent:Intent) {
        // 명령어를 구분하기 위한 걸 임의로 하나 넣어줬는데요. 서비스 쪽으로 전달할 때, 이런 경우도 있고 저런 경우도 잇으니까 다양한 데이터가 전달 될 수 있겠죠.
        // 다양한 데이터가 뭐를 위한 거냐를 우리가 구분하기 위해서 command를 넣어준겁니다.
        val command = intent.getStringExtra("command")
        val name = intent.getStringExtra("name")

        Log.d(TAG, "전달받은 데이터 : $command, $name")

        try {
            Thread.sleep(5000)
        } catch (e: Exception) {}

        val showIntent = Intent(applicationContext, Step3MainActivity::class.java)
        // Intent 객체안에다가 Extra data를 넣고 싶다면 addFlags()를 처음에 넣어줍니다.
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or // 화면이 없는데서 화면이 있는걸 띄울 수 있게 된다.
                Intent.FLAG_ACTIVITY_SINGLE_TOP or // 한번 만들어진걸 재활용해달라.
                Intent.FLAG_ACTIVITY_CLEAR_TOP) // 그 위에 다른 액티비티가 있으면 제거해달라.
        // 일반적으로 이 세가지 옵션을 함께 넣어주는 경우가 많습니다.

        showIntent.putExtra("command", "show")
        showIntent.putExtra("name", "$name from service")
        // 화면이 없는 서비스에서 화면 쪽으로 화면을 띄워달라고 했을 때 문제가 생깁니다.
        // 화면은 태스크라고 하는걸로 묶여있는데, 이 태스크는 내가 만든 화면도 있겠고, 다른 사람들이 만든 앱의 화면도 있을텐데
        // 그 앱의 각각의 화면끼리 필요하면 띄워줄 수 있도록 어떤게 있어요? 우리가 단말에 있는 기본 브라우저 화면이나 전화걸기 화면을 인텐트로 띄울 수 있는데요.
        // 그런 경우 각각의 화면이 연속적으로 뜰 수 있도록 만들어 놓은게 태스크입니다.
        // 화면이 없는데서 화면을 띄우려고 하면 태스크라는 정보가 없어서 화면을 띄우는데 문제가 생길 수 있습니다. 그래서 화면을 띄우려면 Flag 옵션을 줘야됩니다.
        startActivity(showIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() 호출됨")
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }
}
