package bset.hyun.basics.step5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL
/*
    이미지를 가져와서 비트맵으로 만든다음 ImageView에 뿌려주기.
    그런데 한 가지 주의해야 될 것이 있습니다.

    이미지라고 하는 거는 파일 사이즈가 좀 큽니다. 그거를 비트맵 객체로 만들면 메모리에 올라가게 됩니다.
    이거를 반복적으로 여러 개 이미지를 계속 가져오게 되면 메모리가 꽉 차게 됩니다.
    그래서 Out of Memory 같은 에러 문제가 생기게 됩니다.

    그럼 이거를 방지하려면 어떻게 해야 될까요?

    반복적으로 가져올 때마다 메모리에 싸하두는게 아니라 이전에 필요 없는 것들이 있다라고 하게되면
    이전 거를 메모리에서 없애주는게 좋습니다.

 */
class Step5ImageLoadTask : AsyncTask<Any, Any, Bitmap?> {

    private var urlStr: String? = null
    private var imageView: ImageView? = null

    companion object {
        // 요청 url과 비트맵 객체를 매핑을 해놓는 용도입니다.
        // 우리가 이 url로 비트맵을 찾아야 이전 비트맵을 메모리에서 없애줄 수 있기 때문입니다.
        var bitmapHash: HashMap<String, Bitmap> = HashMap()
    }


    constructor(urlStr: String, imageView: ImageView) {
        this.urlStr = urlStr
        this.imageView = imageView
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Any?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            if (bitmapHash.containsKey(urlStr)) {
                var oldBitmap = bitmapHash.remove(urlStr) // 전달된 key에 해당하는 데이터를 삭제고 삭제가 되면 value를 리턴합니다.
                if (oldBitmap != null) {
                    oldBitmap.recycle() // 비트맵을 메모리에서 없애줍니다.
                    oldBitmap = null
                }
            }

            val url = URL(urlStr)
            // 주소로 접속해서 스트림을 받는데 이미지면 이미지 스트림 그래도 넘어오고 그거를 decodeStream이라고해서 비트맵으로 바꿔줍니다.
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            bitmapHash.put(urlStr!!, bitmap) // 이전에 가져왔던건 위에서 지우고 새로 가져온걸 넣어줍니다.

            // 그런데 실제 Universal Image loader나 이런 외부 라이브러리를 쓰게되면 이렇게 안하고
            // 한 번 받아놓은 이미지가 있으면 그냥 그거를 사용합니다.
            // 그러면 이 단말에 파일로 저장해 놓는 방법이 있습니다
            // 보통 이제 캐싱이라고 해서 파일로 저장해 놨다가 같은 url로 요청하면 실제로 서버에 요청하는게 아니라 그 로컬 파일 그대로 로딩해서 그걸 보여줍니다.
            // 그러면 요청한 횟수도 훨씬 줄어듭니다.

            // 근데 이번에는 한번 요청했다가 두 번재 요청할 때는 이전에 요청해서 만든 비트맵을 메모리에서 없애주는 방법만 구현해봤습니다.
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    override fun onProgressUpdate(vararg values: Any?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)

        imageView?.setImageBitmap(result)
        imageView?.invalidate()
    }

}