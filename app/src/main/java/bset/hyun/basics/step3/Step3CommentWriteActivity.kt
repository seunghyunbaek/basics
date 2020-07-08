package bset.hyun.basics.step3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step3_comment_write.*

class Step3CommentWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3_comment_write)

        step3_comment_write_btn_save.setOnClickListener {
            returnToMain()
        }

        val intent = intent
        processIntent(intent)
    }

    private fun processIntent(intent: Intent) {
        if (intent != null) {
            val rating = intent.getFloatExtra("rating", 0.0f)
            step3_comment_write_ratingbar.rating = rating
        }
    }

    private fun returnToMain() {

        val contents = step3_comment_write_edit.text.toString()

        /*
            인텐트 객체는 원래 액션이라고 하는 정보가 기본적으로 들어가게 되어있는데
            시스템에서 아무 일도 안하고 인텐트 객체만 전달하고 싶다면
            그냥 액션없이 비어있는 인텐트를 만들고
            Extra data만 넣어도 됩니다.
         */
        val intent = Intent()
        intent.putExtra("contents", contents)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }


}
