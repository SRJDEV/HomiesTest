package com.deltin.homiestest.viewmodels.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deltin.api.models.Images
import com.deltin.api.repository.APIRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import kotlin.math.log

class ImageViewModel(private val apiRepository: APIRepository) : ViewModel() {

    val _imagesResponse = MutableLiveData<Response<Images>>()
    val imagesResponse : LiveData<Response<Images>> = _imagesResponse



    fun fetchImages(key :String,keyword :String, image_type :String,
                    page :Int) = viewModelScope.launch  {

        try{

            val response =apiRepository.fetchImages(key, keyword, image_type, page)

             Log.d("_FETCH_IMAGES", response.message())
            _imagesResponse.postValue(response)



        }
        catch (err :Exception){


        }

    }
}