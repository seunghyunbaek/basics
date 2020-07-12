package bset.hyun.basics.step4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step4_viewpager.*

class Step4ViewpagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_viewpager)

        val pager = findViewById<ViewPager>(R.id.step4ViewPager)
        pager.offscreenPageLimit = 3 // 캐싱하는게 3개로 늘어남

        val adapter = MoviePagerAdapter(supportFragmentManager)
        val fragment1 = Step4Viewpager1Fragment()
        val fragment2 = Step4Viewpager2Fragment()
        val fragment3 = Step4Viewpager3Fragment()

        adapter.addItem(fragment1)
        adapter.addItem(fragment2)
        adapter.addItem(fragment3)

        pager.adapter = adapter

        step4ViewpagerBtn.setOnClickListener {
            pager.currentItem = 1
        }
    }

    class MoviePagerAdapter : FragmentStatePagerAdapter {

        var items: ArrayList<Fragment> = ArrayList()

        constructor(fm:FragmentManager) : super(fm) {}
        constructor(fm:FragmentManager, behavior:Int ) : super(fm, behavior) {}

        override fun getItem(position: Int): Fragment {
            return items[position]
        }

        override fun getCount(): Int {
            return items.size
        }

        fun addItem(fragment: Fragment) {
            items.add(fragment)
        }

        override fun getPageTitle(position: Int): CharSequence? {
//            return super.getPageTitle(position)
            return "페이지 $position"
        }


    }
}
