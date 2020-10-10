package org.com.raian.flickrcodechallenge.rest

import org.com.raian.flickrcodechallenge.rest.model.FlickrResultApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RestApi {
    @GET("/services/rest/")
    fun fetRemoteData(@QueryMap queryMap: Map<String, String>): Call<FlickrResultApi>
}