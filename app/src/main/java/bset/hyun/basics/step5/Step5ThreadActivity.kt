package bset.hyun.basics.step5

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import bset.hyun.basics.R
import kotlinx.android.synthetic.main.activity_step5_thread.*

/*
스레드는 기본적으로 동시에 뭔가를 실행하기 위해서 생성하는 새로운 어떤 하나의 실행 단위라고 볼 수 있습니다. 이 스레드는 메인 스레드와 별도로 동시에 진행됩니다.
우리가 게임을 만들 때 만약에 우리 비행기가 움직이고 있고 상대방 비행기가 움직이는 슈팅 게임을 만든다고하면 두 개의 비행기가 동시에 움직여야 합니다. 하나가 움직이고 기다렸다가 다른 하나가 움직이고 이렇게 하면 안됩니다.
그래서 그런 경우에 스레드를 만들면 내부에서 알아서 그 리소스를 분산을 해서 CPU에서 처리하거나 뭐 이런 것들을 분산해서 처리를 하게 됩니다.
그리고 이런 스레드라는게 가장 많이 쓰이는 분야 중에 하나가 애니메이션입니다. 이 스레드는 기본적으로 자바의 스레드를 그대로 쓸 수 있습니다.
안드로이드가 자바를 기본으로 하고 있으니까요. 그래서 그대로 쓸 수 있는데 차이가 좀 생기는게 있습니다. 안드로이드의 UI 시스템은 새로 설계가 되었습니다.
그러면서 이 스레드가 동작하는 방식이 표준 자바의 것과 완전히 똑같지는 않습니다.
 */

class Step5ThreadActivity : AppCompatActivity() {

//    var value = 0
    var handler: Step5ValueHandler = Step5ValueHandler()

    var handler2 = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step5_thread)

        step5ThreadBtn1.setOnClickListener {
//            var thread = Step5BackgroundThread()
//            thread.start()

            Thread(Runnable {
                var value = 0
                var running: Boolean = false

                running = true
                while (running) {
                    value += 1

                    // post라고 하는거는 뭔가를 던지는 겁니다. 야구에서 투수가 공을 던지는 걸 post라고 합니다.
                    handler2.post( Runnable() {
                        // 이 안에서는 UI에 접근할 수 있습니다.
                        // 텍스트뷰로 접근하지만 handler라고 하는 쪽으로 전달된 객체가 되고, 핸들러 내부에서 실행되는 코드가 됩니다.
                        // Runnable 안에 들어가 있는 코드고 Runnable 객체가 전달된겁니다. 그래서 여기서는 UI를 접근할 수 있습니다. 왜? 메인스레드에서 실행되기 때문입니다.
                        step5ThreadText1.text = "현재 값 : $value"
                    })

                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                    }
                }
            }).start()
        }

        step5ThreadBtn2.setOnClickListener {
//            step5ThreadText1.text = "현재 값 : $value"

        }
    }

    // 스레드를 객체로 만들고 start()를 실행하면 run() 이 실행되게 된다.
    inner class Step5BackgroundThread : Thread() {
        // Boolean 타입의 플래그를 만드는 이유는 나중에 false로 바꾸면 이게 중단되는 효과가 있기 때문에 보통 이렇게 구성을 합니다.

        var value = 0
        var running: Boolean = false

        override fun run() {
            running = true
            while (running) {
                value += 1

                var message: Message = handler.obtainMessage() // new로 객체를 만들지 않고 메세지 객체를 참조한다.
                var bundle = Bundle()
                bundle.putInt("value", value)
                message.data = bundle
                handler.sendMessage(message)

                try {
                    sleep(1000)
                } catch (e: Exception) {
                }
            }
        }
    }

    inner class Step5ValueHandler : Handler() {
        // 스레드에서 UI를 직접 접근할 수 없으니 Handler로 sendMessage를 보내면 여기서 받게 된다.
        override fun handleMessage(msg: Message) {
            // 메세지를 받아서 UI를 접근하는 데는 문제가 없습니다. 왜냐면 핸들러는 어차피 메인 스레드에서 동작하는 걸로 생각하면 됩니다.
            super.handleMessage(msg)

            var bundle = msg.data
            val value = bundle.getInt("value")
            step5ThreadText1.text = "현재 값 : $value"
        }
    }
}
