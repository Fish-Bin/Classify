package com.fish.classify

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fish.classify.plugins.dOnClick
import com.fish.classify.plugins.route
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOf(arc_view, time_line, wave_view, my_imageview, light_textview, time_view, seek_bar, crash_view).forEachIndexed { index, v ->
            v.dOnClick {
                when (index) {
                    0 -> route(this@MainActivity, ArcViewActivity::class.java)
                    1 -> route(this@MainActivity, TimeLineActivity::class.java)
                    2 -> route(this@MainActivity, WaveViewActivity::class.java)
                    3 -> route(this@MainActivity, BorderImageViewActivity::class.java)
                    4 -> route(this@MainActivity, LightTextViewActivity::class.java)
                    5 -> route(this@MainActivity, ClockTimeViewActivity::class.java)
                    6 -> route(this@MainActivity, SeekBarActivity::class.java)
                    7 -> route(this@MainActivity, CrashViewActivity::class.java)
                }
            }
        }
    }
}
