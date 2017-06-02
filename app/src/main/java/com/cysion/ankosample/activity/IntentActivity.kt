package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        var name = intent.extras.getString("name")
        var age = intent.extras.getInt("age")
        textShow.text = "name:$name,age:$age"
    }
}
