package bset.hyun.basics.step5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_step5_volley.*

/*
Volley 사용법
    RequestQueue라고 하는거를 만들고 RequestQueue에다가 Request 객체를 만들어서 넣어주기만 하면 됩니다.
    그러면 자동으로 요청이 날아갑니다.

    실제로 네트워킹은 스레드를 쓰도록 되어있으니까 스레드 코드가 필요한데
    RequestQueue가 그 안에서 스레드를 생성하고 요청을 보내고 응답을 받아서 스레드가 아닌 메인 스레드에서 UI에 접근할 수 있도록 만들어주는 과정까지 다 알아서 해줍니다.
    우리는 add()라고만 하면 끝나는 겁니다.

    그러면 응답을 받았을 때는 어떻게하느냐?
    Request객체를 만들 때 그 안에 Listener를 미리 등록해놓습니다.
    그러면 RequestQueue가 처리를 다 한다음에 응답을 이쪽으로 전달해주게 됩니다.
    결국에 우리가 하는거는 Request 객체를 만드는 것밖에 없습니다.

    그리고 Volley라고 하는 라이브러리에 RequestQueue가 알아서 전부 다 처리를 해준다고 생각하면 됩니다.

    RequestQueue는 요청시마다 만들어야 할까요? RequestQueue는 한 번만 만들면 됩니다.
    그래서 앱에 보면 애플리케이션이라고 하는 객체를 만들 수 있습니다.
    애플리케이션 객체를 만들어서 거기에 넣어도 되고
    간단한 방법은 새로운 클래스를 만들어 AppHelper라는 이름으로 클래스를 하나 만들어서 여기에 초기화해서 넣어두는 방법이 있습니다.

    일반적으로 애플리케이션 안에다가 넣는 거를 권장을 합니다.
 */
class Step5VolleyActivity : AppCompatActivity() {

    var result: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_volley)

        step5VolleyBtn.setOnClickListener {

            sendRequest()
        }

        // RequestQueue가 여러 번 만들어지면 좋지 않을 겁니다. 그래서 null 일 경우에만 만들어줍니다.
        // 꼭 여기가 아니라 AppHelper 안에서 초기화하는 코드를 넣어도 됩니다.
        if (Step5AppHelper.requestQueue == null) {
            Step5AppHelper.requestQueue = Volley.newRequestQueue(applicationContext)
        }
    }

    fun sendRequest() {
        val url = "https://www.google.co.kr"
        var request:StringRequest = object: StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> {
                println("응답-> $it")
            },
            Response.ErrorListener {
                println("에러 -> ${it.message}")
            }
        ) {
            // 그런데 만약에 요청을 할 때 파라미터를 전달하고 싶다면 특히나 GET이 아니라 POST 방식으로 파라미터를 넣고 싶다고 했을 경우에은
            // 여기에 getParams라고하는 메서드를 재정의합니다.
            override fun getParams(): MutableMap<String, String> {
                // params 안에 추가를 해주면 요청 파라미터로 동작을 합니다.
                var params = HashMap<String, String>()
                return params
            }
        }

        // 그런데 이렇게 넣어줄 때 Volley라고 하는게 내부에서 캐싱을 해줍니다.
        // 그러니까 한 번 보내고 받은 응답 결과가 있으면 그 다음에 보냈을 때 이전 게 있으면 그냥 이전 거를 보여줄 수도 있습니다.
        // 그래서 그렇게 하지 말고 매번 받은 결과를 그대로 보여주세요 라고 하려면 add하기 전에 Request객체에다가 setSouldCache()라고 하는거를 호출해줄 필요가 있습니다.
        request.setShouldCache(false)
        Step5AppHelper.requestQueue?.add(request)
        // 그런데 지금 StringRequest 라고해서 Volley 요청을 보내게 되면 이 요청에 대한 응답은 비동기로 호출이 됩니다.
        // 우리가 요청을 먼저 보내고 나서 응답이 왔을 때 Listener가 호출이 되는 방식입니다.
        // 그러다 보니 우리가 느끼기에는 보내고 나서 바로 응답을 받을 때 까지 기다리는건 아닙니다.
        // 그래서 보통은 요청을 보냈다고 하는 메세지는 여기서 출력을 해줍니다.
        // add라고 해서 요청객체를 추가하고 요청 보냄이란 메세지를 출력해줍니다.
        println("요청 보냄")
    }

    fun println(data: String) {
        step5VolleyText.append(data+"\n")
    }

}
