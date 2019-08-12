package com.fish.classify

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fish.classifyview.bean.CashFlowBean
import kotlinx.android.synthetic.main.activity_carsh_view.*

/**
 * Created by LiuBin on 2017/12/18.
 */

class CrashViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carsh_view)

        val datas = mutableListOf(
                CashFlowBean(rate = 103.53, cfOperAct = 200.36, yearend = "2016/12"),
                CashFlowBean(rate = 92.3, cfOperAct = 43.0, yearend = "2017/06"),
                CashFlowBean(rate = 123.0, cfOperAct = 20.36, yearend = "2017/12"),
                CashFlowBean(rate = 150.6, cfOperAct = 301.36, yearend = "2018/06"))
        crashView.setData(datas)
    }
}
