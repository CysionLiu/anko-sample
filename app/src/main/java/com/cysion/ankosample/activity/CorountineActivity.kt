package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import com.cysion.ankosample.utils.doBgWork
import kotlinx.android.synthetic.main.activity_corountine.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.Ref
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.toast

class CorountineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corountine)
        textShowCorountines.setOnClickListener {
            //这个只是anko-common库的，做对比
//            doAsync {
//                var str = doBgWork()
//                uiThread { ai(Thread.currentThread().name + " $str") }
//            }

            async(UI) {
                var str: String = "before"
                val ref: Ref<CorountineActivity> = this@CorountineActivity.asReference()
                val data: Deferred<String> = bg { doBgWork() }
                str = data.await()
                ref().showSth(str)
            }
        }
    }

    fun showSth(str: String) {
        toast(str)
    }
}
