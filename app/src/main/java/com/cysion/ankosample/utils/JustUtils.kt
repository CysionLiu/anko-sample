package com.cysion.ankosample.utils

import com.cysion.ankosample.utils.LogUtil.ai
import java.util.concurrent.FutureTask

/**
 * Created by CysionLiu on 2017/6/2.
 */
fun doBgWork(): String {
    val futureTask = FutureTask<String>({
        ai("sleep in "+Thread.currentThread().name)
        Thread.sleep(5000)
        "sleep over"
    })
    futureTask.run()
    return futureTask.get()
}