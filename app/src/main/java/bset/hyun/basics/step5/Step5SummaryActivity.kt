package bset.hyun.basics.step5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import bset.hyun.basics.step5.data.Step5SummaryMovieList
import bset.hyun.basics.step5.data.Step5SummaryResponseInfo
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class Step5SummaryActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_summary)

        textView = findViewById(R.id.step5SummaryText)

        val button: Button = findViewById(R.id.step5SummaryBtn)
        button.setOnClickListener {
            requestMovieList()
        }

        if(Step5SummaryAppHelper.requestQueue == null) {
            Step5SummaryAppHelper.requestQueue = Volley.newRequestQueue(applicationContext)
        }
    }

    fun println(data: String) {
        textView.append(data + "\n")
    }

    fun requestMovieList() {
        var url = "http://${Step5SummaryAppHelper.host}:${Step5SummaryAppHelper.port}" + "/movie/readMovieList"
        url += "?" + "type=1"

        val request: StringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> {
                println("응답 받음 -> $it")

                processResponse(it)
            },
            Response.ErrorListener {
                println("에러 발생 -> ${it.message}")
            }
        )

        request.setShouldCache(false)
        Step5SummaryAppHelper.requestQueue?.add(request)
        println("영화목록 요청 보냄.")
    }

    fun processResponse(response: String) {
        val gson = Gson()
        val info = gson.fromJson(response, Step5SummaryResponseInfo::class.java)
        if (info.code == 200) {
            val movieList: Step5SummaryMovieList= gson.fromJson(response, Step5SummaryMovieList::class.java)
            println("영화 갯수 : ${movieList.result.size}")

            for(i in movieList.result.indices) {
                val movieInfo = movieList.result[i]
                println("영화 # $i -> ${movieInfo.id}, ${movieInfo.title}, ${movieInfo.grade}")
            }
        }
    }
}
