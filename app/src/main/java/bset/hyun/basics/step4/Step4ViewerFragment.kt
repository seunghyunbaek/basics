package bset.hyun.basics.step4

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import bset.hyun.basics.R

class Step4ViewerFragment : Fragment() {

    private var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_step4_viewer, container, false)

        imageView = rootView.findViewById<ImageView>(R.id.imageViewer)

        return rootView
    }

    fun setImage(index: Int) {
        imageView?.run {
            if (index == 0) {
                this.setBackgroundColor(Color.RED)
            } else if (index == 1) {
                this.setBackgroundColor(Color.GREEN)
            } else if ( index == 2) {
                this.setBackgroundColor(Color.BLUE)
            }
        }
    }
}