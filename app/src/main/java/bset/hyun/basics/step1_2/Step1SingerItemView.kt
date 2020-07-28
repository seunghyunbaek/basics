package best.hyun.moview.learn1_2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bset.hyun.basics.R

class Step1SingerItemView : LinearLayout {

    lateinit var singerName: TextView
    lateinit var singerMobile: TextView
    lateinit var singerImage: ImageView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.step3_singer_item, this, true)

        singerName = findViewById<TextView>(R.id.singer_name)
        singerMobile = findViewById<TextView>(R.id.singer_mobile)
        singerImage = findViewById(R.id.singer_image)
    }

    fun setName(name: String) {
        singerName.setText(name)
    }

    fun setMobile(mobile: String) {
        singerMobile.setText(mobile)
    }

    fun setImage(resId: Int) {
        singerImage.setImageResource(resId)
    }
}