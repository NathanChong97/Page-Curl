package com.example.pagecurltest.HarismSample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import com.example.pagecurltest.HarismCurl.CurlPage
import com.example.pagecurltest.HarismCurl.CurlView
import com.example.pagecurltest.R


class PageProvider(var context: Context, var pageQuantity: Int = 0) : CurlView.PageProvider {

    // Bitmap resources.
    private val mBitmapIds =
        intArrayOf(
            R.drawable.bear,
            R.drawable.giraffe,
            R.drawable.lion,
            R.drawable.leapord,
            R.drawable.orangutan,
            R.drawable.cheetah
        )

    override fun getPageCount(): Int {
//        return this.pageQuantity
        var pagesCount = 0
        val displaymetrics = DisplayMetrics()
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)
        val wwidth = displaymetrics.widthPixels
        val hheight = displaymetrics.heightPixels
        if (wwidth > hheight) {
            pagesCount = if (mBitmapIds.size % 2 > 0) mBitmapIds.size / 2 + 1 else mBitmapIds.size / 2
        } else {
            pagesCount = mBitmapIds.size
        }
        return pagesCount
    }

    var count = 0
    var newIndex = 0
    //for solving inverted back image
    //https://github.com/harism/android-pagecurl/issues/50
    override fun updatePage(page: CurlPage, width: Int, height: Int, index: Int) {
        //for only front
//        val front: Bitmap = loadBitmap(width, height, index)
//        page.setTexture(front, CurlPage.SIDE_BOTH)
//        page.setColor(
//            Color.argb(127, 255, 255, 255),
//            CurlPage.SIDE_BACK
//        )

//        for front and back
//        if (count == 0) {
//            val front: Bitmap = loadBitmap(width, height, index)
//            val back: Bitmap = loadBitmap(width, height, index + 1)
//            page.setTexture(front, CurlPage.SIDE_FRONT)
//            page.setTexture(back, CurlPage.SIDE_BACK)
//            count++
//        } else{
//            val front: Bitmap = loadBitmap(width, height, index + count)
//            val back: Bitmap = loadBitmap(width, height, index + count + 1)
//            page.setTexture(front, CurlPage.SIDE_FRONT)
//            page.setTexture(back, CurlPage.SIDE_BACK)
//            count++
//        }


        val displaymetrics = DisplayMetrics()
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)
        val wwidth = displaymetrics.widthPixels
        val hheight = displaymetrics.heightPixels


        if (wwidth > hheight) {
            println("case landscape orientation...")
            val front = loadBitmap(width, height, index * 2)
            val back = loadBitmap(width, height, index * 2 + 1)
            val matrix = Matrix()
            matrix.preScale(-1.0f, 1.0f)
            val mirroredBitmap =
                Bitmap.createBitmap(back, 0, 0, back.width, back.height, matrix, false)
            page.setTexture(front, CurlPage.SIDE_FRONT)
            page.setTexture(mirroredBitmap, CurlPage.SIDE_BACK)
        } else {
            println("case portrait orientation...")
            val front = loadBitmap(width, height, index)
            val back = loadBitmap(width, height, index)
            page.setTexture(front, CurlPage.SIDE_FRONT)
            page.setTexture(back, CurlPage.SIDE_BACK)
//            if (count == 0) {
//                val front: Bitmap = loadBitmap(width, height, index)
//                val back: Bitmap = loadBitmap(width, height, index + 1)
//                page.setTexture(front, CurlPage.SIDE_FRONT)
//                page.setTexture(back, CurlPage.SIDE_BACK)
//                count++
//            } else if ((index + count + 1) < pageCount) {
//                val front: Bitmap = loadBitmap(width, height, index + count)
//                val back: Bitmap = loadBitmap(width, height, index + count + 1)
//                page.setTexture(front, CurlPage.SIDE_FRONT)
//                page.setTexture(back, CurlPage.SIDE_BACK)
//                count++
//            }
        }


//        when (index) {
//            // First case is image on front side, solid colored back.
//            0 -> {
//                val front: Bitmap = loadBitmap(width, height, index)
//                page.setTexture(front, CurlPage.SIDE_FRONT)
//                page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK)
//            }
//            // Second case is image on back side, solid colored front.
//            1 -> {
//                val back: Bitmap = loadBitmap(width, height, 1)
//                page.setTexture(back, CurlPage.SIDE_BACK)
//                page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT)
//            }
//            // Third case is images on both sides.
//            2 -> {
//                val front: Bitmap = loadBitmap(width, height, 1)
//                val back: Bitmap = loadBitmap(width, height, 3)
//                page.setTexture(front, CurlPage.SIDE_FRONT)
//                page.setTexture(back, CurlPage.SIDE_BACK)
//            }
//            // Fourth case is images on both sides - plus they are blend against
//            // separate colors.
//            3 -> {
//                val front: Bitmap = loadBitmap(width, height, 2)
//                val back: Bitmap = loadBitmap(width, height, 1)
//                page.setTexture(front, CurlPage.SIDE_FRONT)
//                page.setTexture(back, CurlPage.SIDE_BACK)
//                page.setColor(
//                    Color.argb(127, 170, 130, 255),
//                    CurlPage.SIDE_FRONT
//                )
//                page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK)
//            }
//            // Fifth case is same image is assigned to front and back. In this
//            // scenario only one texture is used and shared for both sides.
//            4 -> {
//                val front: Bitmap = loadBitmap(width, height, 0)
//                page.setTexture(front, CurlPage.SIDE_BOTH)
//                page.setColor(
//                    Color.argb(127, 255, 255, 255),
//                    CurlPage.SIDE_BACK
//                )
//            }
//        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun loadBitmap(width: Int, height: Int, index: Int): Bitmap {
        val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        b.eraseColor(-0x1)
        val c = Canvas(b)
        val d: Drawable = context.resources.getDrawable(mBitmapIds.get(index))
        val margin = 7
        val border = 3
        val r = Rect(margin, margin, width - margin, height - margin)
        var imageWidth = r.width() - border * 2
        var imageHeight = imageWidth * d.intrinsicHeight / d.intrinsicWidth
        if (imageHeight > r.height() - border * 2) {
            imageHeight = r.height() - border * 2
            imageWidth = imageHeight * d.intrinsicWidth / d.intrinsicHeight
        }
        r.left += (r.width() - imageWidth) / 2 - border
        r.right = r.left + imageWidth + border + border
        r.top += (r.height() - imageHeight) / 2 - border
        r.bottom = r.top + imageHeight + border + border
        val p = Paint()
        p.color = -0x3f3f40
        c.drawRect(r, p)
        r.left += border
        r.right -= border
        r.top += border
        r.bottom -= border
        d.bounds = r
        d.draw(c)
        return b
    }

    fun getPageCount1(): Int {
        //return 5;
        var pagesCount = 0
        val displaymetrics = DisplayMetrics()
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)
        val wwidth = displaymetrics.widthPixels
        val hheight = displaymetrics.heightPixels
        if (wwidth > hheight) {
            pagesCount = if (mBitmapIds.size % 2 > 0) mBitmapIds.size / 2 + 1 else mBitmapIds.size / 2
        } else {
            pagesCount = mBitmapIds.size
        }
        return pagesCount
    }
}