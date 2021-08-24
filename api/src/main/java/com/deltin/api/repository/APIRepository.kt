package com.deltin.api.repository

import com.deltin.api.models.Images
import com.deltin.api.network.HomiesClient
import retrofit2.Response
import retrofit2.http.Query

class APIRepository {


    suspend fun fetchImages( key :String,keyword :String, image_type :String,
                              page :Int ): Response<Images> =
        HomiesClient.api.fetchImages(key, keyword, image_type, page)


}