package bset.hyun.basics.step4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import bset.hyun.basics.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class Step4TabActivity : AppCompatActivity() {

    lateinit var fragment1: Step4ToolbarTabFragment1
    lateinit var fragment2: Step4ToolbarTabFragment2
    lateinit var fragment3: Step4ToolbarTabFragment3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_tab)

        // xml에 레이아웃에 만든 툴바를 이렇게 해야 액션바로 설정이 됩니다
        val toolbar: MaterialToolbar = findViewById(R.id.step4Toolbar)
        setSupportActionBar(toolbar)

        fragment1 = Step4ToolbarTabFragment1()
        fragment2 = Step4ToolbarTabFragment2()
        fragment3 = Step4ToolbarTabFragment3()

        supportFragmentManager.beginTransaction().add(R.id.step4ToolbarContainer, fragment1).commit()

        val tabs = findViewById<TabLayout>(R.id.step4Tabs)
        tabs.addTab(tabs.newTab().setText("친구"))
//        tabs.addTab(tabs.newTab().setText("일대일채팅"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_launcher_foreground))
        tabs.addTab(tabs.newTab().setText("기타"))

        tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                var selected: Fragment? = null

                if (position == 0) {
                    selected = fragment1
                } else if (position == 1) {
                    selected = fragment2
                } else if (position == 2) {
                    selected = fragment3
                } else {
                    selected = fragment1
                }

                supportFragmentManager.beginTransaction().replace(R.id.step4ToolbarContainer, selected).commit()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}