package com.deltin.homiestest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deltin.api.models.Hit
import com.deltin.api.repository.APIRepository
import com.deltin.homiestest.R
import com.deltin.homiestest.adapters.PictureListAdapter
import com.deltin.homiestest.utility.hideProgreeDialog
import com.deltin.homiestest.utility.showProgressDialog
import com.deltin.homiestest.viewmodels.images.ImageViewModel
import com.deltin.homiestest.viewmodels.images.ImageViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_dialog.*

class MainActivity : AppCompatActivity() {

    private lateinit var listOfImages : ArrayList<Hit>
    private lateinit var apiRepository: APIRepository
    private lateinit var imageViewModelFactory: ImageViewModelFactory
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var adapter: PictureListAdapter

    private var isLoading  = false
    private var hasPagination = false
    private var pageNo = 1
    private var query = ""

    private  var layoutManager =  GridLayoutManager(this,2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewImages.layoutManager = layoutManager


        apiRepository= APIRepository()
        listOfImages = ArrayList()

        imageViewModelFactory= ImageViewModelFactory(apiRepository)
        imageViewModel= ViewModelProvider(this,imageViewModelFactory).get(ImageViewModel::class.java)


        adapter = PictureListAdapter(listOfImages,this)
        recyclerViewImages.adapter = adapter


        recyclerViewImages.addOnScrollListener(recyclerViewOnScrollListener)


        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    this@MainActivity.query = query
                    searchView.clearFocus()

                    fetchImages(query,true)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    this@MainActivity.query = newText
                }


                return false
            }


        })
        btnSearch.setOnClickListener(View.OnClickListener {

            searchView.clearFocus()
            fetchImages(query,true)

        })

        observeFetchedImages()

    }

    fun fetchImages(query : String,isFromSearch: Boolean){
        showProgressDialog(this,"Loading...")

        Log.d("QUERY", "fetchImages: "+query+"Page:" +pageNo)
        if(isFromSearch){
            resetPagination()
        }
        imageViewModel.fetchImages( getString(R.string.picxabayKey), query,"photo,",pageNo)

    }

    fun resetPagination(){
        isLoading = false
        hasPagination =false
        pageNo =1
        listOfImages.clear()
        adapter.setListOfPictures(listOfImages)
    }

    fun observeFetchedImages(){

        imageViewModel.imagesResponse.observe(this, {
            hideProgreeDialog()
            if (it.isSuccessful && it.body() != null) {
                isLoading = false


                if (it.body()!!.totalHits > 20) {

                    var totalRecords = it.body()?.totalHits
                    var totalPages = (totalRecords)?.div(20) ?: -1

                    if(totalRecords!! % 20 != 0)
                        totalPages += 1

                    hasPagination = pageNo < totalPages

                } else {
                    hasPagination = false
                }

                listOfImages.addAll(it.body()!!.hits as ArrayList<Hit>)
                adapter.setListOfPictures(listOfImages)
                //Log.d("TAG", "observeFetchedImages: "+listOfImages.get(0).previewURL)

                adapter.notifyDataSetChanged()


                Log.d("QUERY", "TOTALHITS "+it.body()?.totalHits)
                Log.d("QUERY", "TOTALHITS "+ ((it.body()?.totalHits)?.div(20) ?: -1))


//                Toast.makeText(
//                    this,
//                    "" +listOfImages.size,
//                    Toast.LENGTH_LONG
//                ).show()

            } else {

                Toast.makeText(
                    this,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })


    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = layoutManager.getChildCount()
                val totalItemCount: Int = layoutManager.getItemCount()
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && hasPagination ) {

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0) {

                        isLoading = true
                        pageNo ++

                        fetchImages(query,false)

                    }
                }
            }
        }


}