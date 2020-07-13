package bset.hyun.basics.step5

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step5_http.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Step5HttpActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var textView: TextView

    var urlstr: String? = null
    val handler: Handler = Handler()

    var result: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_http)

        editText = findViewById(R.id.step5HttpEdit)
        textView = findViewById(R.id.step5HttpText)

        step5HttpBtn.setOnClickListener {
            urlstr = editText.text.toString()

            val thread = RequestThread()
            thread.start()
        }
    }

    fun println(data: String) {
        Log.d("Step5HttpActivity", data)
        handler.post {
            textView.text = data
        }
    }

    inner class RequestThread : Thread() {
        override fun run() {

            try {
                val url = URL(urlstr) // URL 객체 생성
//                val conn: HttpsURLConnection? = url.openConnection() as HttpsURLConnection
                val conn: HttpURLConnection? = url.openConnection() as HttpURLConnection
                if (conn != null) {
                    conn.connectTimeout = 10000
                    conn.requestMethod = "GET"
                    conn.doInput = true // 서버에서 받는 것
                    conn.doOutput = true // 서버로 보내는 것

                    // responseCode를 호출하면 이 떄 연결을 하게 됩니다.
                    val resCode = conn.responseCode // res코드가 리턴됩니다.
                    // 정상적인 코드가 넘어오면 코드를 확인한 후에 한줄씩 읽어서 화면에 뿌릴 수 있습니다.
//                    if (resCode == HttpsURLConnection.HTTP_OK) { }
                    // conn.inputStream은 들어오는 데이터를 받을 수 있는 통로를 만들 수 있게 됩니다.
                    // 이거를 BufferedReader라고해서 reader객체로 변환을 하는데 conn.inputStream으로 뽑아낸 InputStream객체를 바로 감싸줄 수 없어서 InputStreamSReader라고 하는거로 먼저 감싸줘야합니다.
                    var reader: BufferedReader = BufferedReader(InputStreamReader(conn.inputStream))
                    // Reader라고 하는건 바이트 배열이 아니라 문자열로 처리할 수 있는 거고
                    // BufferedReader는 한 줄씩 읽어들일 수 있습니다.

                    var line: String? = null

                    while (true) {
                        line = reader.readLine()

                        if(line == null) {
                            break
                        }
                        result = "$result \n$line"
                    }
                    reader.close()
                    println(result)

                    conn.disconnect()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
