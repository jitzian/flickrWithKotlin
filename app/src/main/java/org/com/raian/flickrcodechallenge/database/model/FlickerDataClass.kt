package org.com.raian.flickrcodechallenge.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flicker")
data class FlickerDataClass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = 0L,
    @ColumnInfo(name = "owner") var owner: String? = null,
    @ColumnInfo(name = "secret") var secret: String? = null,
    @ColumnInfo(name = "server") var server: String? = null,
    @ColumnInfo(name = "farm") var farm: Long? = 0L,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "ispublic") var isPublic: String? = null,
    @ColumnInfo(name = "isfriend") var isFriend: Long? = 0L,
    @ColumnInfo(name = "isfamily") var isFamily: Long? = 0L
)