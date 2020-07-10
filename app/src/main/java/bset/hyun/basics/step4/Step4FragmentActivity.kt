package bset.hyun.basics.step4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step4_fragment.*

class Step4FragmentActivity : AppCompatActivity() {

    lateinit var fragment1: Step4MainFragment
    lateinit var fragment2: Step4MenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_fragment)

        fragment1 = Step4MainFragment()
        fragment2 = Step4MenuFragment()

        step4FragmentBtn1.setOnClickListener {
//            val fragment1 = Step4MainFragment() // View라고 하면 객체 생성시 Context객체가 필요한데 프래그먼트는 필요가 없다.
//            supportFragmentManager.beginTransaction().add(R.id.step4MainFragmentContainer, fragment1).commit()
            supportFragmentManager.beginTransaction().replace(R.id.step4MainFragmentContainer, fragment1).commit()
        }

        step4FragmentBtn2.setOnClickListener {
//            val fragment2 = Step4MenuFragment() // 이런 식으로 매번 객체를 생성하면 메모리에 계속 생성이 되니까 클래스 멤버 변수로 선언한다.
            supportFragmentManager.beginTransaction().replace(R.id.step4MainFragmentContainer, fragment2).commit()
        }
    }

    fun onFragmentChange(index: Int) {
        if (index == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.step4MainFragmentContainer, fragment1).commit()
        } else if (index == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.step4MainFragmentContainer, fragment2).commit()
        }
    }
}
