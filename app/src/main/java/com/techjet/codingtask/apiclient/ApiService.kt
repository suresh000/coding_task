package com.techjet.codingtask.apiclient

import com.techjet.codingtask.model.list.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1

    @GET("services/feeds/photos_public.gne?format=json")
    suspend fun listAPI(
        @Query("tags") tags: String,
        @Query("nojsoncallback") callback: Int
    ): Response<ListResponse>

}