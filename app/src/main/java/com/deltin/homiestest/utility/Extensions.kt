package com.deltin.homiestest.utility

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.deltin.homiestest.R
import com.google.android.material.snackbar.Snackbar


fun EditText.afterTextChangedChangeButtonImage(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
            Log.d("text", editable.toString())
        }
    })
}



fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {

    Snackbar.make(this, message, duration).show()

}

fun ImageView.loadImage(url: String, context: Context) {

    Log.d("IMAGE_URL", url)
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .thumbnail(Glide.with(context).load(url).apply(RequestOptions().override(100,100)))
        .into(this)
}