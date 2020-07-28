package best.hyun.moview.learn3

import android.os.Parcel
import android.os.Parcelable

// Parcel은 뭘까?
// Parcel은 객체안에 있는 데이터를 다른데 전달할 때 사용되는 객체이다.

class Step3SimpleData() : Parcelable {

    var number:Int = 0
    var message:String? = ""

    constructor(n:Int, m:String) : this() {
        number = n
        message = m
    }

    constructor(parcel: Parcel) : this() { // Parcel 객체를 받았을 때
        // parcel에 데이터가 동일하게 들어가 있다.
        // Parcel에서 SimpleData로 복원한다.
        parcel.run {
            number = readInt() // Integer 타입의 데이터를 하나 읽어서 number 변수에 할당하겠다.
            message = readString() // String 타입의 데이터를 하나 읽어서 message 변수에 할당하겠다.
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    // Parcel 객체로 써준다는 말. 즉 'Parcel 객체로 만들어준다' 는 의미
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        // SimpleData가 Parcel로 바꿔준다.
        dest?.run {
            writeInt(number)
            writeString(message)
        }
    }

    // CREATOR 객체는 필수로 들어간다.
    companion object CREATOR : Parcelable.Creator<Step3SimpleData> {
        override fun createFromParcel(parcel: Parcel): Step3SimpleData {
            return Step3SimpleData(parcel)
        }

        override fun newArray(size: Int): Array<Step3SimpleData?> {
            return arrayOfNulls(size)
        }
    }



}