package bset.hyun.basics.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bset.hyun.basics.R

class Step4ToolbarTabFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_step4_toolbar_tab2, container, false) as ViewGroup

        return rootView
    }

    
}