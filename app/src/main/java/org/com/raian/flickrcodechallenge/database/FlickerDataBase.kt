package org.com.raian.flickrcodechallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.dataBaseVersion
import org.com.raian.flickrcodechallenge.database.dao.FlickerDao
import org.com.raian.flickrcodechallenge.database.model.FlickerDataClass

@Database(
    entities = [FlickerDataClass::class],
    exportSchema = false,
    version = dataBaseVersion
)
abstract class FlickerDataBase : RoomDatabase() {
    abstract fun flickerDao(): FlickerDao
}