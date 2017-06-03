package com.cysion.ankosample.utils

import android.content.Context
import org.jetbrains.anko.*

/**
 * Created by cysionliu on 2017/6/2.
 */
object LogUtil : AnkoLogger {

    fun av(str: Any?) {
        verbose(str)
    }

    fun ad(str: Any?) {
        debug(str)
    }

    fun ai(str: Any?) {
        info(str)
    }

    fun aw(str: Any?) {
        warn(str)
    }

    fun ae(str: Any?) {
        error(str)
    }
    fun Context.errors(str: Any?) {
        error(str)
    }
}

