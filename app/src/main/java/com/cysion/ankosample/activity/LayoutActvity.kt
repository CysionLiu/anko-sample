package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import org.jetbrains.anko.*

class LayoutActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_layout_actvity)
        val ID  = 0x123123
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
                id = ID
            }
        }
        find<Button>(ID).setOnClickListener { toast("this is login button") }


    }
}
