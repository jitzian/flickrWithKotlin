package org.com.raian.flickrcodechallenge.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.com.raian.flickrcodechallenge.database.model.FlickerDataClass

@Dao
interface FlickerDao {
    //TODO: Improve this and add interface. Also use suspend function

    @Insert(onConflict = REPLACE)
    fun insert(flickerDataClass: FlickerDataClass)

    @Query("DELETE FROM flicker")
    fun deleteAll()

    @Query("SELECT * FROM flicker ORDER BY id ASC")
    fun getAll(): List<FlickerDataClass>

    @Query("SELECT * FROM flicker WHERE id = (:id)")
    fun getById(id: String): FlickerDataClass

}