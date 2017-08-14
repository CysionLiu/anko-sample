package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import com.cysion.ankosample.utils.LogUtil.ad
import com.cysion.ankosample.utils.LogUtil.ae
import com.cysion.ankosample.utils.LogUtil.ai
import com.cysion.ankosample.utils.LogUtil.av
import com.cysion.ankosample.utils.LogUtil.aw
import com.cysion.ankosample.utils.doBgWork
import kotlinx.android.synthetic.main.activity_common.*
import org.jetbrains.anko.*

class CommonActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        //
        textToIntent1.setOnClickListener {
            startActivity<IntentActivity>("name" to "Jack", "age" to 12)
        }
        //
        textToIntent2.setOnClickListener {
            startActivity(intentFor<IntentActivity>("name" to "Lily", "age" to 13).singleTop())
        }
        //
        textToBrowser.setOnClickListener {
            browse("http://m.baidu.com")
        }
        //
        textToLog.setOnClickListener {
            verbose("tag-default class")//unable to print
            debug(110)
            warn(null)
            info(listOf<String>("today", "is", "a", "fine", "day"))
            error(HashMap<String, String>().apply {
                put("Jack", "12")
                put("Lily", "13")
            })
            //other
            av("tag-default class")
            ad("debug")
            aw(null)
            ai(listOf<String>("today", "is", "a", "fine", "day"))
            ae(HashMap<String, String>().apply {
                put("Jack", "12")
                put("Lily", "13")
            })
        }
        //
        textToDimension.setOnClickListener {
            //context,ankocontext,fragment,view can be used all
            info(dip(100))//dp->px
            info(px2dip(100))//px->dp
        }
        //
        textToHelpers.setOnClickListener {
            var builder = StringBuilder().apply {
                append("displayMetrics.widthPixels:$displayMetrics\n")
            }.toString()
            info(builder)
            info(attempt { 3 }.value)//substitute for try..catch?
            info(attempt { 1 / 0 }.error)//
            doFromSdk(21) {
                info("print from api 21")
            }
            doIfSdk(21) {
                packageManager.getPackageInfo(packageName, 0).versionName
                info("print only api is 21")
            }
            doAsync {
                var str = doBgWork()
                uiThread { info(Thread.currentThread().name + " $str") }
            }
        }
        //
        textToAlert.setOnClickListener {
            alert("this is the msg", "title") {
                yesButton { toast("button-yes") }
                noButton { toast("button-no") }
            }.show()
        }
        //
        textToAlert2.setOnClickListener {

            alert("this is the msg") {
                customTitle {
                    verticalLayout {
                        imageView(R.mipmap.ic_launcher)
                        editText { hint = "hint_title" }
                    }
                }
                okButton { toast("button-ok") }
                cancelButton { toast("button-cancel") }
            }.show()
        }
        //
        textToSelector.setOnClickListener {
            val countries = listOf("Russia", "USA", "England", "Australia")
            selector("Where are you from?", countries) { ds, i ->
                toast("So you're living in ${countries[i]}, right?")
            }
        }
    }
}
