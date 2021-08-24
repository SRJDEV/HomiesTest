package com.deltin.homiestest.activities

import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.deltin.homiestest.R
import com.deltin.homiestest.utility.loadImage
import kotlinx.android.synthetic.main.activity_full_screen_image.*


class FullScreenImage : AppCompatActivity() {

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mGestureDetector: GestureDetector? = null

    private var mScaleFactor = 1.0f
    private var mImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        var url = intent.getStringExtra(getString(R.string.image_url_key))

        mImageView = full_imageView

        if(url!= null)
        mImageView?.loadImage(url, this)

        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
        mGestureDetector = GestureDetector(this, GestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleGestureDetector?.onTouchEvent(event)
        return true;
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        mScaleGestureDetector?.onTouchEvent(ev);
        mGestureDetector?.onTouchEvent(ev);
        return mGestureDetector?.onTouchEvent(ev)!!


    }


    inner class ScaleListener : SimpleOnScaleGestureListener() {

        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(
                0.1f,
                Math.min(mScaleFactor, 10.0f)
            )
            mImageView?.setScaleX(mScaleFactor)
            mImageView?.setScaleY(mScaleFactor)

            //horizontalScrollview.setScaleX(mScaleFactor)
           // horizontalScrollview.setScaleY(mScaleFactor)



            return true
        }
    }

    inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // event when double tap occurs
        override fun onDoubleTap(e: MotionEvent): Boolean {
            // double tap fired.
            return true
        }
    }
}