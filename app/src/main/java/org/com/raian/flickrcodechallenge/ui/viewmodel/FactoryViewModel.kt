package org.com.raian.flickrcodechallenge.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import org.com.raian.flickrcodechallenge.constans.GlobalConstants
import org.com.raian.flickrcodechallenge.repository.database.FlickerDataBase

@Suppress("UNCHECKED_CAST")
class FactoryViewModel(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayFetchedDataViewModel::class.java)) {
            val db = Room.databaseBuilder(context, FlickerDataBase::class.java, GlobalConstants.dataBaseName).build()
            return DisplayFetchedDataViewModel(db, context) as T
        }
        throw IllegalArgumentException("ViewModel does not exist")
    }
}