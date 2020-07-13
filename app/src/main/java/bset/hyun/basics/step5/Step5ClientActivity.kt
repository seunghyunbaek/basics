package bset.hyun.basics.step5

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step5_client.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

/*

네트워킹
서버 - 데이터를 보내줄 수 있는 기능
클라이언트 - 데이터를 어떤 걸 보내달라고 요청

클라이언트가 요청을 하고 서버가 응답 하는거를 기본적인 네트워킹 방식이라고 할 수 있습니다. 그래서 이거를 클라이언트 서버 모델, C/S 모델이라고 합니다.

네트워킹에 가장 기본은 TCP/IP 라고 하는 겁니다. 그래서 클라이언트하고 서버가 어떤 연결을 만들고 그 연결을 통해서 데이터를 주고받는 그런 형태를 생각할 수 있습니다.
PC하고 PC사이에 인터넷으로 연결했다고 하는거는 중간에 어떤 파이프가 연결돼있다고 생각하면 쉽습니다. 파이프를 통해서 물을 흘려보내거나 또는 어떤 물건을 전달하거나 하는 경우를 생각해보면 됩니다.
근데 안드로이드에서는 **반드시 스레드**를 사용하게 돼있습니다. 인터넷 기능을 사용하면 스레드를 사용해야 됩니다. 스레드를 이용해서 UI 업데이트를 위해서는 **반드시 핸들러**를 쓸 수 밖에 없습니다.
물론 이제 이 핸들러를 내부적으로 다루는 AsyncTask같은거를 쓰면 겉으로는 핸들러라고 하는게 보이지는 않습니다.

소켓
소켓은 네트워킹의 가장 기본이라고 할 수 있습니다.
그래서 TCP/IP라고 하는게 네트워킹에서 많이 나오는데 단순히 생각하면 우리가 서버 쪽의 기능을 만든 다음에 클라이언트 서버로 데이터를 요청해서 응답받는 거를 생각해보면 됩니다.

웹
웹이라고 하면 보통 인터넷이라고 알고 있습니다.


 */

class Step5ClientActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_client)

        textView = findViewById(R.id.step5ClientText)

        step5ClientBtn.setOnClickListener {
            val thread = ClientThread()
            thread.start()
        }
    }

    /*
        네트워킹 코드를 만드는데 액티비티에 넣었을 경우에 언제든 종료될 수 있습니다.
        그래서 서비스로 변환해야 된다고 하는 얘기를 들을 수 있습니다.
        서비스로 쪽으로 옮겨주는게 훨씬 좋은 방법이라고 할 수가 있습니다.
     */
    class ClientThread : Thread() {
        override fun run() {
            val host = "127.0.0.1"
            val port = 5003
            // 서버에 접속하려면 Socket()을 해주면 됩니다. 여기서 어떤 컴퓨터에 접속을 할거냐해서 IP를 지정해줘야 합니다.
            Log.d("ClientThread", "ClientThread 실행")
            try {
                val socket = Socket(host, port)
                // socket.getOutputStream()이라고 하면 보내기 위한 통를 만들게 됩니다.
                val outstream = ObjectOutputStream(socket.getOutputStream())
                outstream.writeObject("안녕!")
                outstream.flush()

                Log.d("ClientThread", "서버로 보냄")
                val instream = ObjectInputStream(socket.getInputStream())
                val input = instream.readObject()
                Log.d("ClientThread", "받은 데이터 : $input")

                // error : UI는 메인스레드에서 관리하기 때문에
//                textView.text = "받은 데이터 : $input"

//                handler.post {
//                    textView.text = "받은 데이터 : $input"
//                }

            } catch (e: Exception) {
                Log.d("ClientThread", "클라이언트 에러 발생 + ${e.message}")
                e.printStackTrace()
            }

        }
    }
}
