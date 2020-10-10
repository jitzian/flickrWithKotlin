package org.com.raian.flickrcodechallenge.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("owner")
    @Expose
    var owner: String? = null
    @SerializedName("secret")
    @Expose
    var secret: String? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("farm")
    @Expose
    var farm: Long? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ispublic")
    @Expose
    var ispublic: Long? = null
    @SerializedName("isfriend")
    @Expose
    var isfriend: Long? = null
    @SerializedName("isfamily")
    @Expose
    var isfamily: Long? = null

}
