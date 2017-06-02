package com.cysion.ankosample.utils

import com.cysion.ankosample.activity.LayShowActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by cysionLiu on 2017/6/2.
 */
class LayoutActyUI : AnkoComponent<LayShowActivity> {
    val ET_ID = 0x1001
    override fun createView(ui: AnkoContext<LayShowActivity>) = with(ui) {
        verticalLayout {
            val name = editText("LayoutActyUI") {
                id = ET_ID
            }
            button("Say Hello") {
                onClick {
                    ctx.toast("Hello, ${name.text}!")
                    name.textColor = 0xffff0000.toInt()
                }
            }
        }
    }
}

