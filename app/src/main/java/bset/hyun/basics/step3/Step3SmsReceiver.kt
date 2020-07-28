package best.hyun.moview.learn3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Step3SmsReceiver : BroadcastReceiver() {

    private val TAG: String = "Learn3SmsReceiver"

    private val format:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() 호출됨")

        val bundle = intent.extras // 번들이 해시테이블처럼 Extra data를 담고있는 애이다.
        val messages = bundle?.let { parseSmsMessage(it) }

        if (messages != null) {
            if (messages.isNotEmpty()) {
                val sender = messages[0]!!.originatingAddress // 발신번호
                Log.d(TAG, "sender: $sender")

                val contents = messages[0]!!.messageBody.toString() // 메세지 내용
                Log.d(TAG, "contents: $contents")

                val receiveDate = Date(messages[0]!!.timestampMillis) // 수신시각
                Log.d(TAG, "received date: $receiveDate")

                sendToActivity(context, sender, contents, receiveDate)
            }
        }
    }

//    private fun parseSmsMessage(bundle: Bundle): Array<SmsMessage?>? {
//        val objs = bundle["pdus"] as Array<Any>?
//        val messages = arrayOfNulls<SmsMessage>(objs!!.size)
//        for (i in objs!!.indices) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                val format = bundle.getString("format")
//                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
//            } else {
//                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
//            }
//        }
//        return messages
//    }


    private fun parseSmsMessage(bundle: Bundle) : Array<SmsMessage?> {
        // pdus는 SMS 데이터를 처리하는 국제표준 프로토콜이 smpp라고 있는데 그 안에 데이터가 이런 이름이라고 들어 있습니다.
        // pdus안에 SMS 데이터와 관련된 내용들이 들어가 있다고 보면 됩니다.
        // SMS가 80바이트 이내로 해서 여러개를 받을 수 있습니다. 프로토콜 자체는.
        val objs = bundle["pdus"] as Array<Any>
        var messages = arrayOfNulls<SmsMessage>(objs.size)

        for(i in objs.indices) {
            // get해서 pdu라고 하는 데이터를 뽑아낸걸 던져주면 된다.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format:String? = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }

    private fun sendToActivity(context: Context, sender:String?, contents: String, receivedDate: Date?) {
        val intent = Intent(context, Step3SmsActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TOP)

        intent.putExtra("sender", sender)
        intent.putExtra("contents", contents)
        intent.putExtra("date", format.format(receivedDate))

        context.startActivity(intent)
    }

}
