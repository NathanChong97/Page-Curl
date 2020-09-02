package com.example.pagecurltest.EschaoSample

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pagecurltest.R

class EschaoActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    var mPageFlipView: PageFlipView? = null
    var mGestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harism)

        mPageFlipView = PageFlipView(this)
        setContentView(mPageFlipView)
        mGestureDetector = GestureDetector(this, this)

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            mPageFlipView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    override fun onResume() {
        super.onResume()
        LoadBitmapTask.get(this).start()
        mPageFlipView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPageFlipView!!.onPause()
        LoadBitmapTask.get(this).stop()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.optionmenus, menu)
        val duration = mPageFlipView!!.animateDuration
        if (duration == 1000) {
            menu.findItem(R.id.animation_1s).isChecked = true
        } else if (duration == 2000) {
            menu.findItem(R.id.animation_2s).isChecked = true
        } else if (duration == 5000) {
            menu.findItem(R.id.animation_5s).isChecked = true
        }
        if (mPageFlipView!!.isAutoPageEnabled) {
            menu.findItem(R.id.auoto_page).isChecked = true
        } else {
            menu.findItem(R.id.single_page).isChecked = true
        }
        val pref = PreferenceManager
            .getDefaultSharedPreferences(this)
        val pixels = pref.getInt("MeshPixels", mPageFlipView!!.pixelsOfMesh)
        when (pixels) {
            2 -> menu.findItem(R.id.mesh_2p).isChecked = true
            5 -> menu.findItem(R.id.mesh_5p).isChecked = true
            10 -> menu.findItem(R.id.mesh_10p).isChecked = true
            20 -> menu.findItem(R.id.mesh_20p).isChecked = true
            else -> {
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var isHandled = true
        val pref = PreferenceManager
            .getDefaultSharedPreferences(this)
        val editor = pref.edit()
        when (item.itemId) {
            R.id.animation_1s -> {
                mPageFlipView!!.animateDuration = 1000
                editor.putInt(Constants.PREF_DURATION, 1000)
            }
            R.id.animation_2s -> {
                mPageFlipView!!.animateDuration = 2000
                editor.putInt(Constants.PREF_DURATION, 2000)
            }
            R.id.animation_5s -> {
                mPageFlipView!!.animateDuration = 5000
                editor.putInt(Constants.PREF_DURATION, 5000)
            }
            R.id.auoto_page -> {
                mPageFlipView!!.enableAutoPage(true)
                editor.putBoolean(Constants.PREF_PAGE_MODE, true)
            }
            R.id.single_page -> {
                mPageFlipView!!.enableAutoPage(false)
                editor.putBoolean(Constants.PREF_PAGE_MODE, false)
            }
            R.id.mesh_2p -> editor.putInt(Constants.PREF_MESH_PIXELS, 2)
            R.id.mesh_5p -> editor.putInt(Constants.PREF_MESH_PIXELS, 5)
            R.id.mesh_10p -> editor.putInt(Constants.PREF_MESH_PIXELS, 10)
            R.id.mesh_20p -> editor.putInt(Constants.PREF_MESH_PIXELS, 20)
            R.id.about_menu -> {
                showAbout()
                return true
            }
            else -> isHandled = false
        }
        if (isHandled) {
            item.isChecked = true
            editor.apply()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            mPageFlipView!!.onFingerUp(event.x, event.y)
            return true
        }
        return mGestureDetector!!.onTouchEvent(event)
    }

    override fun onShowPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        mPageFlipView!!.onFingerDown(e!!.x, e.y)
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        mPageFlipView!!.onFingerMove(e2!!.x, e2.y)
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    private fun showAbout() {
        val aboutView = layoutInflater.inflate(
            R.layout.about, null,
            false
        )
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setTitle(R.string.app_name)
        builder.setView(aboutView)
        builder.create()
        builder.show()
    }
}