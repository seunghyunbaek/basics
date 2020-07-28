package best.hyun.moview.learn3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step3_menu.*

class Step3MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3_menu)

        learn3_menu_btn.setOnClickListener {
            val intent = Intent().apply {
                putExtra("name", "mike")
            }

            setResult(Activity.RESULT_OK, intent)

            finish()
        }

        val passedIntent = intent
        processIntent(passedIntent)
    }

    private fun processIntent(intent:Intent?) {
        if(intent != null) {
            val names:ArrayList<String>? = intent.getSerializableExtra("names") as ArrayList<String>

            if(names != null) {
                Toast.makeText(applicationContext, "전달받은 이름 리스트 갯수 : ${names.size}", Toast.LENGTH_SHORT).show()
            }

            val data = intent.getParcelableExtra<Step3SimpleData>("data")
            if(data != null) {
                Toast.makeText(applicationContext, "전달받은 SimpleData : ${data.message}, ${data.number}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
