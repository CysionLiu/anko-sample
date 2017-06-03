package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ScrollView
import com.cysion.ankosample.R
import kotlinx.android.synthetic.main.activity_layout_actvity.*
import org.jetbrains.anko.act
import org.jetbrains.anko.sdk25.coroutines.onScrollChange
import org.jetbrains.anko.startActivity

class LayoutActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_actvity)
        textToShow1.setOnClickListener {
            startActivity<LayShowActivity>("id" to 1)
        }

        textToShow2.setOnClickListener {
            startActivity<LayShowActivity>("id" to 2)
        }

        textToShow3.setOnClickListener {
            startActivity<LayShowActivity>("id" to 3)
        }
    }
}
