package com.cysion.ankosample.utils

import org.jetbrains.anko.*

/**
 * Created by xianshang.liu on 2017/6/2.
 */
object LogUtil : AnkoLogger {

     fun av(str: Any?) {
        verbose(str)
    }

     fun ad(str: Any?) {
        debug(str)
    }

     fun  ai(str: Any?) {
        info(str)
    }

     fun aw(str: Any?) {
        warn(str)
    }

     fun ae(str: Any?) {
        error(str)
    }
}