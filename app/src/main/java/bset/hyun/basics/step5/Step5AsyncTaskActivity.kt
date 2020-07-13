package bset.hyun.basics.step5

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step5_async_task.*


/*

스레드에서 UI를 접근할 때는 핸들러를 쓰는게 필요한데 그런거 없이 그냥 AsyncTask라고 하는 클래스를 정의하고 그걸 가지고 객체를 만들면
그 안에서 스레드로 동작하는 코드와 UI로 접근하는 코드를 메서드로만 분리를 해서 코드를 같이 넣어 놓을 수 있다는 장점이 있습니다.

 */


class Step5AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_async_task)

        step5AsyncBtn1.setOnClickListener {
            var task = Step5AsyncProgressTask()
            task.execute("Start")
        }
    }


    //
    inner class Step5AsyncProgressTask : AsyncTask<String, Int, Int>() {
        var value:Int = 0

        // 스레드 안에 넣는 코드를 이 안에다 넣으면 됩니다. 그러니까 스레드 안에서 어떤 게 동작하게 하겠다는 메서드입니다.
        override fun doInBackground(vararg params: String?): Int { // <String, Int, Int >에서 첫번째 String은 이 메서드의 파라미터로 String을 받는 다는 의미이며 가변 길이 파라미터이므로 몇 개의 문자열이건 전달해줄 수가 있습니다.
            // 처리를 하는 코드는 이 곳에 작성합니다.
            while(true) {
                if (value >= 100)
                    break

                value += 4

                publishProgress(value) // onProgressUpdate()가 자동으로 호출됩니다.

                try { Thread.sleep(2000) } catch (e: Exception) { e.printStackTrace() }
            }

            return value
        }

        override fun onProgressUpdate(vararg values: Int?) { // <String, Int, Int>에서 두번째 정의한 Int가 파라미터 타입으로 설정됩니다.
            // 중간중간 UI를 업데이트하고 싶다고하면 이 메서드가 호출되는 시점에 여기다가 해주면 됩니다.
            // 그럼 이 메서드가 우리가 원할 때 호출이 돼야 되는데 doInBackground 안에서 publishProgress 라고 하면 이 메서드가 호출됩니다.
            super.onProgressUpdate(*values)

            step5AsyncProgressBar.setProgress(values[0]!!.toInt())
        }

        override fun onPostExecute(result: Int?) { // <String, Int, Int> 세번째 정의한 Int가 파라미터 타입으로 정의됩니다.
            // doInBackround 과정이 끝나고 나면 여기서 완료가 되게 됩니다.
            // doInBackground에서 return 된 것이 여기 파라미터로 넘어오게 됩니다.
            super.onPostExecute(result)

            Toast.makeText(applicationContext, "Complete", Toast.LENGTH_SHORT).show()
        }
    }
}
