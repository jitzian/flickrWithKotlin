package org.com.raian.flickrcodechallenge.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photos {

    @SerializedName("page")
    @Expose
    var page: Long? = null
    @SerializedName("pages")
    @Expose
    var pages: Long? = null
    @SerializedName("perpage")
    @Expose
    var perpage: Long? = null
    @SerializedName("total")
    @Expose
    var total: String? = null
    @SerializedName("photo")
    @Expose
    var photo: List<Photo>? = null

}
