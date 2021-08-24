package com.deltin.homiestest.utility

import android.app.Dialog
import android.content.Context
import android.view.*

import com.deltin.homiestest.R
import kotlinx.android.synthetic.main.progress_dialog.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


private lateinit var progressDailog :Dialog

fun showProgressDialog(context: Context, text: String)
{
    progressDailog = Dialog(context)
    progressDailog.setContentView(R.layout.progress_dialog)
    val window: Window? = progressDailog.window
    progressDailog.setCancelable(false)
    window?.setLayout(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    window?.setBackgroundDrawableResource(android.R.color.transparent)
    val wlp: WindowManager.LayoutParams? = window?.attributes

    wlp?.gravity = Gravity.CENTER
    wlp?.flags = wlp?.flags
    WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
    window?.attributes = wlp
    progressDailog.tvProgress_text.text =text
    progressDailog.show()
}

fun hideProgreeDialog() {
    if(::progressDailog.isInitialized &&  progressDailog.isShowing) {
        progressDailog.dismiss()
    }

}

fun convertDate(inputDateStr :String) :String{

    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")

    val date: Date = inputFormat.parse(inputDateStr)
    return outputFormat.format(date)
}

//private fun showCustomDialog(viewGroup: ViewGroup, location: String) {
//    //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//
//
//    //then we will inflate the custom alert dialog xml that we created
//    val dialogView: View =
//        LayoutInflater.from(context).inflate(R.layout.add_sublocation_dialog, viewGroup, false)
//
//
//    val btnAddLocation = dialogView.btnAddSubLocation
//    val etLocation = dialogView.tvLocation
//    val etSubLocation = dialogView.etSubLocation
//    val tvError = dialogView.tvError
//
//
//    dialogView.tvLocation.text = location
//
//
//    //Now we need an AlertDialog.Builder object
//    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//
//    //setting the view of the builder to our custom view that we already inflated
//    builder.setView(dialogView)
//
//
//    //finally creating the alert dialog and displaying it
//    val alertDialog: AlertDialog = builder.create()
//
//
//
//    btnAddLocation.setOnClickListener{
//        if(etLocation.text.toString().isEmpty() || etSubLocation.text.toString().isEmpty()){
//            tvError.visibility = View.VISIBLE
//        }
//        else{
//            tvError.visibility = View.GONE
//
//        }
//    }
//    alertDialog.show()
//}