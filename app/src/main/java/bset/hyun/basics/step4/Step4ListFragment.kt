package bset.hyun.basics.step4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import bset.hyun.basics.R

class Step4ListFragment : Fragment() {
    var activity: Step4Fragment2Activity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = getActivity() as Step4Fragment2Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_step4_list, container, false)

        val button = rootView.findViewById<Button>(R.id.step4ListBtn1)
        button.setOnClickListener {
            activity?.onImageChange(0)
        }

        val button2 = rootView.findViewById<Button>(R.id.step4ListBtn2)
        button2.setOnClickListener {
            activity?.onImageChange(1)
        }

        val button3 = rootView.findViewById<Button>(R.id.step4ListBtn3)
        button3.setOnClickListener {
            activity?.onImageChange(2)
        }

        return rootView
    }


}