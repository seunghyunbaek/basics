package bset.hyun.basics.step3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step3_summary.*

class Step3SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3_summary)

        step3_summary_btn.setOnClickListener {
            showCommentWriteActivity();
        }
    }

    private fun showCommentWriteActivity() {
        val rating = step3_summary_ratingbar.rating

        val intent = Intent(applicationContext, Step3CommentWriteActivity::class.java)
        intent.putExtra("rating", rating)

        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == 101 && intent != null) {
            val contents = intent.getStringExtra("contents")
            step3_summary_text.text = contents
        }

    }
}
