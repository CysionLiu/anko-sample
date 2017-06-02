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
            startActivity<IntentActivity>("name" to "小明", "age" to 12)
        }
        //
        textToIntent2.setOnClickListener {
            startActivity(intentFor<IntentActivity>("name" to "小红", "age" to 13).singleTop())
        }
        //
        textToBrowser.setOnClickListener {
            browse("http://m.baidu.com")
        }
        //
        textToLog.setOnClickListener {
            verbose("tag-默认为调用类")//貌似不会打印
            debug(110)
            warn(null)
            info(listOf<String>("today", "is", "a", "fine", "day"))
            error(HashMap<String, String>().apply {
                put("小明", "12")
                put("小红", "13")
            })
            //另一种用法
            av("tag-默认为调用类")
            ad("debug")
            aw(null)
            ai(listOf<String>("today", "is", "a", "fine", "day"))
            ae(HashMap<String, String>().apply {
                put("小明", "12")
                put("小红", "13")
            })
        }
        //
        textToDimension.setOnClickListener {
            //context,ankocontext,fragment,view都可以用
            info(dip(100))//dp->px
            info(px2dip(100))//px->dp
        }
        //
        textToHelpers.setOnClickListener {
            var builder = StringBuilder().apply {
                append("displayMetrics.widthPixels:$displayMetrics\n")
            }.toString()
            info(builder)
            info(attempt { 3 }.value)//替代try..catch?
            info(attempt { 1 / 0 }.error)//打印异常
            doFromSdk(21) {
                info("从api 21开始打印")
            }
            doIfSdk(21) {
                packageManager.getPackageInfo(packageName, 0).versionName
                info("只有api 21才打印")
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
