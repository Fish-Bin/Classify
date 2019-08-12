package com.fish.classify.plugins

import android.content.Context
import android.content.Intent
import android.view.View
import com.fish.classify.listener.DoubleClickListener

/**************************************
 * function : 向已有的类添加方法,避免子类的生成
 *
 * Created on 2018/7/2  11:38
 * @author mnlin
 **************************************/

/**
 * 添加onClick防抖动监听
 */
fun <T : View> T.dOnClick(action: T.() -> Unit) {
    this.setOnClickListener(object : DoubleClickListener() {
        override fun doClick(v: View) {
            action(this@dOnClick)
        }
    })
}

fun route(context: Context, cls: Class<*>) {
    context.startActivity(Intent(context, cls))
}