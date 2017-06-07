package com.cysion.ankosample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cysion.ankosample.R
import com.cysion.ankosample.utils.LogUtil.ai
import com.cysion.ankosample.utils.database
import com.cysion.ankosample.utils.rAddress
import com.cysion.ankosample.utils.rAge
import com.cysion.ankosample.utils.rName
import kotlinx.android.synthetic.main.activity_sqlite.*
import org.jetbrains.anko.attempt
import org.jetbrains.anko.db.*
import org.jetbrains.anko.sdk25.coroutines.onClick

//注意数据库降级可能报错
class SQliteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
//        val db = database.writableDatabase//第一种方式
        database.use { }//第二种方式，执行完{}后，数据库完全关闭
        //创建表
        textToCreate.onClick {
            database.use {
                createTable("Person", true, "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                        "name" to TEXT,
                        "age" to INTEGER,
                        "address" to TEXT
                )
            }
            display.text = "ifNotExists,创建表Person成功"
        }
        //插入数据
        textToInsert.onClick {
            database.use {
                if (attempt {
                    var name: String = rName()
                    var address: String = rAddress()
                    var age: Int = rAge()
                    insert("Person",
                            "age" to age,
                            "name" to name,
                            "address" to address
                    )
                    display.text = "insert(Person,age to $age,name to $name,address to $address)"
                }.isError) {
                    display.text = "错误,可能不存在表"
                }
            }
        }
        //查询语句
        textToWhereArgs.onClick {
            database.use {
                transaction {
                    // Your transaction code
                }
                if (attempt {
                    val whereArgs = select("Person", "name", "age", "address")
                            .whereArgs("(age > {userId}) and (name = {userName})",
                                    "userName" to "小红",
                                    "userId" to 20)
                    val parseList = whereArgs.parseList(classParser<Person>())
                    ai(Course(parseList, 12))
                    display.text = parseList.toString()
                }.isError) {
                    display.text = "错误,可能不存在表"
                }
            }
        }

        //删除表
        textToDropTable.onClick {
            database.use {
                if (attempt {
                    dropTable("Person")
                    display.text = "删除表Person成功"
                }.isError) {
                    display.text = "错误,可能不存在表"
                }
            }
        }
        //删除某条数据
        textToDel.onClick {
            if (attempt {
                database.use {
                    execSQL("delete from Person where name = '小红'")
                }
            }.isError) {
                display.text = "错误,可能不存在表"
            }
        }
        //更新某条数据
        textToUpdate.onClick {
            if (attempt {
                database.use {
                    execSQL("update Person set name = '小红' where name = '小明'")
                    display.text = "更新age<23变成小红成功"
                }
            }.isError) {
                display.text = "错误,可能不存在表"
            }
        }
        //自定义行解析器
        customRowParser.onClick {
            if (attempt {
                database.use {
                    val whereArgs = select("Person", "name", "age", "address")
                            .whereArgs("id = {ids}",
                                    "ids" to 2)
                    val result = whereArgs.parseSingle(rowParser
                    { name: String, age: Int, address: String ->
                        Triple(name, age, address)
                    })
                    display.text = with(result) { "name-$first-second-$second-third-$third" }
                }
            }.isError) {
                display.text = "错误,可能不存在表"
            }
        }

        showAll.onClick {
            attempt {
                database.use {
                    val result = select("Person", "name", "age", "address")
                    display.text = result.parseList(classParser<Person>()).toString()
                }
            }
        }
    }
}

data class Person(val name: String, val age: Int, val address: String)
data class Course(var personList: List<Person>, var numOfPerson: Int)

