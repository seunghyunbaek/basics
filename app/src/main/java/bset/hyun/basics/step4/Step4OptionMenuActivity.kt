package bset.hyun.basics.step4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import bset.hyun.basics.R

class Step4OptionMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_option_menu)

//        val abar: ActionBar? = supportActionBar
//        abar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_step4, menu)
        return true
    }

    // 메뉴 아이템이 클릭됐을 떄 호출되는 콜백 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val curId = item.itemId

        when (curId) {
            R.id.step4_menu_refresh -> {
                Toast.makeText(this, "새로고침 메뉴 클릭 됨", Toast.LENGTH_SHORT).show()
            }

            R.id.step4_menu_search -> {
                Toast.makeText(this, "검색 메뉴 클릭 됨", Toast.LENGTH_SHORT).show()
            }

            R.id.step4_menu_settings -> {
                Toast.makeText(this, "설정 메뉴 클릭 됨", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
