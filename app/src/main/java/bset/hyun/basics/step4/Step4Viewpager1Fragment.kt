package bset.hyun.basics.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bset.hyun.basics.R

class Step4Viewpager1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: ViewGroup = inflater.inflate(R.layout.fragment_step4_viewpager1, container, false) as ViewGroup

        return rootView
    }
}