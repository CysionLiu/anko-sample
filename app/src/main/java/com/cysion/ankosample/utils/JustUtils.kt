package com.cysion.ankosample.utils

import com.cysion.ankosample.utils.LogUtil.ai
import java.util.*
import java.util.concurrent.FutureTask

/**
 * Created by CysionLiu on 2017/6/2.
 */
fun doBgWork(): String {
    val futureTask = FutureTask<String>({
        ai("sleep in " + Thread.currentThread().name)
        Thread.sleep(5000)
        "sleep over"
    })
    futureTask.run()
    return futureTask.get()
}

val listNames = listOf<String>("小明", "小红", "小伟", "线程", "地方", "肉疙瘩", "频繁的", "吗二")

fun rName(): String {
    return listNames[Random().nextInt(listNames.size)]
}

val listAddress = listOf<String>("北京", "上海", "杭州", "深圳", "天津", "南京", "青岛", "成都")

fun rAddress(): String {
    return listAddress[Random().nextInt(listAddress.size)]
}

val listAges = listOf<Int>(23, 12, 14, 25, 32, 34, 53, 11, 17, 31)

fun rAge(): Int {
    return listAges[Random().nextInt(listAges.size)]
}