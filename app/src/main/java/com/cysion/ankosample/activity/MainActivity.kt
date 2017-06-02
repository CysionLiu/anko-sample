package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textToCommon.setOnClickListener {
            startActivity<CommonActivity>();
        }
        textToLayout.setOnClickListener {
            startActivity<LayoutActvity>();
        }
        textToCorountines.setOnClickListener {
            startActivity<CorountineActivity>();
        }
        textToSQLite.setOnClickListener {
            startActivity<SQliteActivity>();
        }
    }
}
