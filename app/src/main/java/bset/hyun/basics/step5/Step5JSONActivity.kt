package bset.hyun.basics.step5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_step5_json.*

/*
    JSON
    자바스크립트의 객체 포맷이라고 생각을 하면 됩니다.
    중괄호를 이용해서 객체를 표현하고,
    대괄호를 이용해서 배열을 표현합니다.

    중괄호로 표현되는 객체 안에는 :으로 구분된 속성이 들어가고, 속성의 키와 밸류로 구성이 됩니다.
    그리고 각각의 속성은 , 로 구분이 됩니다.

    이게 전부 다 입니다.

    Volley는 이미지 처리기능이 좋지 못해 Universal Image Loader라던가 다른 외부 라이브러리를 사용한다.
 */

class Step5JSONActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_json)

        step5JSONBtn.setOnClickListener {
            sendRequest()
        }

        step5JSONBtn2.setOnClickListener {
            sendImageRequest()
        }

        if (Step5AppHelper.requestQueue == null) {
            Step5AppHelper.requestQueue  = Volley.newRequestQueue(applicationContext)
        }
    }

    fun sendImageRequest() {
        val url: String = "https://movie-phinf.pstatic.net/20200624_236/15929657006462MdUF_JPEG/movie_image.jpg?type=m886_590_2"

        val task = Step5ImageLoadTask(url, step5JSONImage)
        task.execute()
    }

    fun sendRequest() {
        val url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101"

        val request: StringRequest = object: StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> {
//                println("응답 -> $it")

                processResponse(it)
            },
            Response.ErrorListener {
                println("에러 -> ${it.message}")
            }
        )  {
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String, String>()

                return params
            }
        }

        request.setShouldCache(false)
        Step5AppHelper.requestQueue?.add(request)
        println("요청 보냄")
    }

    fun processResponse(response: String) {
        val gson = Gson()
        val movieList = gson.fromJson(response, Step5MovieList::class.java)

        if (movieList != null) {
            val countMovie = movieList.boxOfficeResult?.dailyBoxOfficeList?.size
            println("박스오피스 타입 : ${movieList.boxOfficeResult?.boxofficeType}")
            println("응답받은 영화 개수 : $countMovie")
        }
    }

    fun println(data: String) {
        step5JSONText.append(data + "\n")
    }
}
