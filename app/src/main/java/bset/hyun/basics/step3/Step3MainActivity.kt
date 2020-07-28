package best.hyun.moview.learn3

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step3_main.*

class Step3MainActivity : AppCompatActivity() {

    // on으로 시작되는 메서드는 대부분이 콜백 메서드라고해서 내가 아니라 시스템이나 다른 쪽에서 자동으로 그 상태에 맞춰서 호출한다.
    override fun onCreate(savedInstanceState: Bundle?) { // 메모리에 액티비티가 만들어지고 액티비티로서 처음에 초기화 되는 시점에 호출됨. 시작점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3_main)

        // 권한이 있는지 체크해보기
        val permissionCheck: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)

        // 권한이 주어져있는지 아닌지 알 수 있다.
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) { // PERMISSION_GRANTED는 권한이 주어져있다는걸 얘기한다.
            Toast.makeText(this, "SMS 수신 권한 주어져 있음", Toast.LENGTH_SHORT).show()
        } else { // 권한이 없을 때
            Toast.makeText(this, "SMS 수신 권한 없음", Toast.LENGTH_SHORT).show()

            // 권한에 대한 설명이 필요한지 아닌지 이걸로 체크할 수 있다.
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                // 권한에 대한 설명이 필요하다면
//                Toast.makeText(this, "SMS 권한 설명 필요함", Toast.LENGTH_SHORT).show()
//            } else { // 설명이 필요없다면 바로 권한을 부여할 수 있다.
                // 권한을 부여해달라는 대화상자는 시스템에서 띄워줘서 우리는 시스템쪽으로 그거를 요청하면 됩니다.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1) // 시스템이 권한에 대한 대화상자를 띄워줍니다.
//            }
        }

        Toast.makeText(this, "onCreate() 호출됨", Toast.LENGTH_SHORT).show()

        lrn3_btn.setOnClickListener {
            val intent = Intent(application, Step3MenuActivity::class.java)
//            startActivity(intent)
            val names = ArrayList<String>()
            names.add("김진수")
            names.add("황수연")

            intent.putExtra("names", names)

            val data = Step3SimpleData(100, "Hello")
            intent.putExtra("data", data)

            startActivityForResult(intent, 101)
        }

        lrn3_btn2.setOnClickListener {
            val receiver = lrn3_edit2.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$receiver"))
            startActivity(intent)

            val intent2 = Intent()
            val name = ComponentName("best.hyun.moview.learn3", "best.hyun.moview.learn3.Learn3_MenuActivity")
            intent2.component = name
            startActivity(intent2)
        }


        lrn3_btn3.setOnClickListener {
            finish()
        }

        lrn3_btn4.setOnClickListener {
            val name = lrn3_edit4.text.toString()

            val intent = Intent(applicationContext, Step3Service::class.java)
            intent.putExtra("command", "show")
            intent.putExtra("name", name)
            startService(intent)
        }
        
        lrn3_btn5.setOnClickListener {
            val intent = Intent(applicationContext, Step3Service::class.java)
            stopService(intent)
        }

        val passedIntent = intent
        processCommand(passedIntent)
    }

    override fun onStart() { // onCreate()다음 호출됨.
        super.onStart()
        Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() { // 화면이 보여지기 바로 전에 호출됨, 가장 중요함, 복구되기 바로 전에 호출됨
        super.onResume()
        Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_SHORT).show()

        val pref:SharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        if(pref != null) {
            val name = pref.getString("name", "")
            Toast.makeText(this, "복구된 이름 : $name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() { // 화면이 포커스를 잃었을 때, 가장 중요함, 중지되는 시점에 바로 호출됨
        // 일반적으로 onPause() 상태에서 필요한 데이터를 저장함
        // 파일이나, 데이터베이스, sharedPreferences에 저장할 수 있다
        super.onPause()
        Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_SHORT).show()

        val pref:SharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = pref.edit() // 해시 테이블과 같음. put으로 넣고 get으로 뺀다.
        editor.putString("name", "소녀시대")
        editor.commit() // commit을 호출해야 저장됨
    }

    override fun onStop() { // 화면이 안보여 완전히 중지된 상태
        super.onStop()
        Toast.makeText(this, "onStop() 호출됨", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() { // 메모리에서 리소스가 다 없어지는 걸 얘기함. onCreate()와 반대
        super.onDestroy()
        Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("name")
            Toast.makeText(applicationContext, "메뉴화면으로부터 응답 : $name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        // 이미 액티비티가 만들어져 있는 상태에서는 onCreate()가 호출되지 않고 이게 호출됩니다.
        // 우리가 서비스에서 onCreate()는 한 번만 호출되고 나중에 onStartCommand()가 호출되는 것과 같습니다.
        processCommand(intent)

        super.onNewIntent(intent)
    }

    private fun processCommand(intent:Intent?) {
        if(intent != null) {
            val command = intent.getStringExtra("command")
            val name = intent.getStringExtra("name")

            Toast.makeText(this, "서비스로부터 전달받은 데이터 : $command, $name", Toast.LENGTH_SHORT).show()
        }

    }


    // 시스템이 정상적으로 권한이 부여가 됐다 아니다를 받으면 우리쪽으로 알려주는데 콜백이 사용됩니다.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults) // 놔둬도 되지만 주석처리함
        when(requestCode) {
            1 -> {
                if (grantResults.size > 0) {
                    // getResults[0]에 권한이 들어있다.
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // 사용자가 수락했다
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 승인함", Toast.LENGTH_SHORT).show()
                    } else if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 거부함", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "SMS 수신 권한을 부여받지 못함", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
