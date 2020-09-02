package com.example.pagecurltest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pagecurltest.EschaoSample.EschaoActivity
import com.example.pagecurltest.HarismSample.HarismActivity
import kotlinx.android.synthetic.main.activity_main.eschaoButton
import kotlinx.android.synthetic.main.activity_main.harismButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        harismButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, HarismActivity::class.java))
        }

        eschaoButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, EschaoActivity::class.java))
        }

//        var index = 0
//        if (lastNonConfigurationInstance != null) {
//            index = lastNonConfigurationInstance as Int
//        }
//
//        curl.setPageProvider(PageProvider(this, 6))
//        curl.setSizeChangedObserver(object : CurlView.SizeChangedObserver {
//            override fun onSizeChanged(width: Int, height: Int) {
//                if (width > height) {
//                    curl.setViewMode(CurlView.SHOW_TWO_PAGES)
//                    curl.setMargins(.1f, .05f, .1f, .05f)
////                    curl.setMargins(0f, 0f, 0f, 0f)
//
//                } else {
//                    curl.setViewMode(CurlView.SHOW_ONE_PAGE)
//                    curl.setMargins(.1f, .1f, .1f, .1f)
////                    curl.setMargins(0f, 0f, 0f, 0f)
//
//                }
//            }
//        })
//
//        curl.setCurrentIndex(index)
//        curl.setBackgroundColor(-0xdfd7d0)
////        curl.setEnableTouchPressure(true)
////        curl.setAllowLastPageCurl(false)
    }

//    override fun onPause() {
//        super.onPause()
//        curl.onPause()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        curl.onResume()
//    }
//
//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return curl.getCurrentIndex()
//    }
}