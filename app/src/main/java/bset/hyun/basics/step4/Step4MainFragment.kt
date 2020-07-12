package bset.hyun.basics.step4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import bset.hyun.basics.MainActivity
import bset.hyun.basics.R

class Step4MainFragment : Fragment() {

    var activity: Step4FragmentActivity? = null

    override fun onAttach(context: Context) { // 액티비티 위에 올라간다는 의미, onCreate 보다 먼저 호출됨
        super.onAttach(context)

        activity = getActivity() as Step4FragmentActivity
    }

    override fun onDetach() { // 내려간다는 의미
        super.onDetach()

        activity = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: ViewGroup = inflater.inflate(R.layout.fragment_step4_main, container, false) as ViewGroup

        val mainBtn = rootView.findViewById<Button>(R.id.step4MainFragmentBtn)
        mainBtn.setOnClickListener {
            activity?.onFragmentChange(1)
        }

        return rootView
    }


}