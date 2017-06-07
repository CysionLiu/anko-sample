package com.cysion.ankosample.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.cysion.ankosample.utils.LogUtil.ai
import org.jetbrains.anko.db.*

/**
 * Created by xianshang.liu on 2017/6/6.
 * 注意数据库降级可能报错
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db?.createTable("test", true, "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "name" to TEXT,
                "age" to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion == 2) {
            if (oldVersion < newVersion) {
                ai("newVersion--$newVersion")
                db.execSQL("  ALTER TABLE Person RENAME TO temp_person")
                db?.createTable("Person", true, "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                        "name" to TEXT,
                        "age" to INTEGER,
                        "address" to TEXT,
                        "sex" to INTEGER
                )
                db.execSQL("INSERT INTO Person SELECT id, name, age,address,'' FROM temp_person")
                db.dropTable("temp_person")
            }
        }
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(getApplicationContext())

