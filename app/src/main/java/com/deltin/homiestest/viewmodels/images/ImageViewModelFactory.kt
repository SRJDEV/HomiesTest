package com.deltin.homiestest.viewmodels.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deltin.api.repository.APIRepository

class ImageViewModelFactory(private  val apiRepository : APIRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ImageViewModel(apiRepository) as T

}