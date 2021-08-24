package com.deltin.homiestest.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.deltin.api.models.Hit
import com.deltin.api.models.Images
import com.deltin.homiestest.R
import com.deltin.homiestest.activities.FullScreenImage
import com.deltin.homiestest.utility.loadImage
import kotlinx.android.synthetic.main.images_row.view.*

class PictureListAdapter(var items : ArrayList<Hit> , val context : Context):
    RecyclerView.Adapter<PictureListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.images_row,parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageViewPicture.loadImage(items.get(position).previewURL,context = context)


    }

    override fun getItemCount(): Int {

       return items.size
    }

    fun setListOfPictures(items: ArrayList<Hit>){
        this.items = items
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var imageViewPicture: ImageView

        init {


                imageViewPicture = view.imageViewRecycler

                view.setOnClickListener {

                var intent  = Intent(context,FullScreenImage::class.java)

                intent.putExtra(context.resources.getString(R.string.image_url_key),items.get(adapterPosition).largeImageURL)



                context.startActivity(intent)

//                    Toast.makeText(
//                         context,
//                   " Clicked",
//                        Toast.LENGTH_LONG
//                    ).show()
            }
        }


    }


}