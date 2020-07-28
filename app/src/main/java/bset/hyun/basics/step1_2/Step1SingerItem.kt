package best.hyun.moview.learn1_2

data class Step1SingerItem(var name:String="", var mobile: String="", var resId:Int?=null) {

    override fun toString(): String {
        return "SingerItem { name='$name', mobile='$mobile'}"
    }
}
