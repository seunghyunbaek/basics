package bset.hyun.basics.step4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import bset.hyun.basics.R

class Step4Fragment2Activity : AppCompatActivity() {

    private var fragment1: Step4ListFragment? = null
    private var fragment2: Step4ViewerFragment? = null

    private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4_fragment2)

//        fragment1 = Step4ListFragment()
//        fragment2 = Step4ViewerFragment()

        fragmentManager = supportFragmentManager
        fragment1 = fragmentManager!!.findFragmentById(R.id.listFragment) as Step4ListFragment?
        fragment2 = fragmentManager!!.findFragmentById(R.id.viewerFragment) as Step4ViewerFragment?
    }

    fun onImageChange(index: Int) {
        fragment2!!.setImage(index)
    }
}
