package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.cysion.ankosample.utils.LayoutActyUI
import com.cysion.ankosample.utils.LogUtil.ai
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onSeekBarChangeListener

class LayShowActivity : AppCompatActivity() {

    val BTN_ID = 0x1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.extras.getInt("id")
        ai(id)
        when (id) {
            1 -> showFirstStyle()
            2 -> showSecondStyle()
            3 -> showThirdStyle()
            4 -> showFirstStyle()
        }
    }

    private fun showThirdStyle() {
        verticalLayout {
            button("seekbar") {
                textSize = 26f
            }.lparams(width = wrapContent) {
                horizontalMargin = dip(15)
                topMargin = dip(20)
            }
            seekBar {
                onSeekBarChangeListener {
                    onProgressChanged { seekBar, progress, fromUser ->
                        ai("progress--$progress")
                    }
                }
            }.lparams(width = dip(200)) {
                horizontalMargin = dip(25)
                topMargin = dip(20)
            }
        }
    }

    private fun showSecondStyle() {
        LayoutActyUI().setContentView(this)
    }

    private fun showFirstStyle() {
        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                textSize = 26f
                id = BTN_ID
            }
        }
        find<Button>(BTN_ID).setOnClickListener { toast("this is login button") }
    }
}
