package bset.hyun.basics.step6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R

class Step6SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step6_summary)

        // 데이터베이스 사용을 위한 준비단계
        Step6AppHelper.openDatabase(applicationContext, "movie")
        Step6AppHelper.createTable("outline")

        // 영화 상세 정보 movie 테이블
        // 한줄평 review 테이블 ( comment는 예약어이기 때문에 쓸 수 없음 )

        // 영화 API를 접속해서 영화 목록을 가져왔을 때 outline 테이블에 저장하기
        // 요청을 하려고 하는 시점에 인터넷이 연결되어 있다면 요청하고 응답 받아서 로컬에 저장하면 되는데
        // 그 다음에 인터넷이 끊어져있다면 데이터베이스에서 조회하면 됩니다.
    }
}
