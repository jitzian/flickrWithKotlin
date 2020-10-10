package org.com.raian.flickrcodechallenge.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FlickrResultApi {

    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("stat")
    @Expose
    var stat: String? = null

}
