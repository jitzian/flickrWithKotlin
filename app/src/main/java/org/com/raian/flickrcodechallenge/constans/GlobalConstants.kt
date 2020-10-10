package org.com.raian.flickrcodechallenge.constans

class GlobalConstants {
    companion object {
        const val baseURL = "https://api.flickr.com/"
        const val flickrKey = "dc2242530334eff5c97106c9110de945"
        const val totalOfColumns = 3

        const val methodValue = "flickr.photos.search"
        const val formatValue = "json"
        const val noJsonCallbackValue = "1"
        const val safeSearchValue = "1"

        //Constants for saving RoomDB
        const val dataBaseName = "flicker.db"
        const val dataBaseVersion = 1
    }
}