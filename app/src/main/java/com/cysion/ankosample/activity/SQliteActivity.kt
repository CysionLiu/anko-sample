package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import com.cysion.ankosample.utils.LogUtil.ai
import org.jetbrains.anko.attempt
import java.io.File
import java.io.FileOutputStream

class SQliteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        var i = 0;
        if (attempt {
            i = 10
            FileOutputStream(File("sdf")).close()
            i=20
        }.isError) {
            ai(i)
        }
    }
}
