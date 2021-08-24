package com.deltin.api.network

import com.deltin.api.models.Images
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomiesAPI {

    @GET("api/")
    suspend fun fetchImages(@Query ("key") key :String,
                            @Query ("q") keyword :String,
                            @Query ("image_type") image_type :String,
                            @Query ("page") page :Int,

                            ) : Response<Images>


}