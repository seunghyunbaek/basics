package best.hyun.moview.learn1_2

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.step1_2_.*

class Step1_2_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.step1_2_)

        val btn: Button = findViewById(R.id.testBtn)
        btn.setOnClickListener {
            val toast: Toast = Toast.makeText(applicationContext, "위치가 바뀐 토스트", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 200, 200)
            toast.show()

//            Intent(this, DetailActivity::class.java).let {
//                startActivity(it)
//            }
        }

        testBtn2.setOnClickListener {
            val inflater = layoutInflater
            val layout: View = inflater.inflate(
                R.layout.step1_2_toastborder,
                findViewById<ViewGroup>(R.id.toast_layout_root)
            )

            val text = layout.findViewById<TextView>(R.id.testText)
            text.text = "모양을 바꾼 토스트"

            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.CENTER, 0, -100)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout
            toast.show()
        }

        testBtn3.setOnClickListener {
            Snackbar.make(it, "스낵바입니다", Snackbar.LENGTH_SHORT).show()
        }

        testBtn4.setOnClickListener {
            showMessage()
        }

        val container = findViewById<FrameLayout>(R.id.testContainer)

        testBtn5.setOnClickListener {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.step3_sub1, container, true)
        }

        val listView = findViewById<ListView>(R.id.testListView)

        val adapter = SingerAdapter()
        adapter.addItem(
            Step1SingerItem(
                "사람1",
                "010-1000-1000"
            )
        )
        adapter.addItem(
            Step1SingerItem(
                "사람2",
                "010-2000-2000"
            )
        )
        adapter.addItem(
            Step1SingerItem(
                "사람3",
                "010-3000-3000"
            )
        )
        adapter.addItem(
            Step1SingerItem(
                "사람4",
                "010-4000-4000"
            )
        )
        adapter.addItem(
            Step1SingerItem(
                "사람5",
                "010-5000-5000"
            )
        )

        listView.adapter = adapter
        listView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                testScrollView.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })

        listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item: Step1SingerItem = adapter.getItem(position) as Step1SingerItem
                Toast.makeText(applicationContext, "선택: ${item.name}", Toast.LENGTH_SHORT).show()
            }
        })

        textBtn6.setOnClickListener {
            adapter.addItem(
                Step1SingerItem(
                    "사람${adapter.count + 1}",
                    "010-0000-0000"
                )
            )
            adapter.notifyDataSetChanged()
        }

        val items = arrayOf("물건1", "물건2", "물건3", "물건4")
        val spinner = findViewById<Spinner>(R.id.testspinner)
        val spinnerAdapter:ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                testsptv.setText("선택 : ")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                testsptv.setText(items[position])
            }
        }
    }

    private fun showMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("안내")
        builder.setMessage("종료하시겠습니까?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("예", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Snackbar.make(testBtn, "예 버튼이 눌렸습니다", Snackbar.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton("아니오", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Snackbar.make(testBtn, "아니오 버튼이 눌렸습니다", Snackbar.LENGTH_SHORT).show()
            }
        })

        val dialog = builder.create()
        dialog.show()
    }

    inner class SingerAdapter : BaseAdapter() {

        var items: ArrayList<Step1SingerItem> = ArrayList()

        fun addItem(item: Step1SingerItem) {
            items.add(item)
        }

        override fun getItem(position: Int): Any {
            return items.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            var view: SingerItemView = SingerItemView(applicationContext)
            var view: Step1SingerItemView? = null
            if(convertView == null) {
                view =
                    Step1SingerItemView(applicationContext)
            } else {
                view = convertView as Step1SingerItemView
                println("ConvertView 사용")
            }

            val item = items.get(position)
            view.setName(item.name)
            view.setMobile(item.mobile)

            item.resId?.let {
                view.setImage(it)
            }

            return view
        }
    }

}
