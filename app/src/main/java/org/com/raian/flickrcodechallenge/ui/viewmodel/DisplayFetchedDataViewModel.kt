package org.com.raian.flickrcodechallenge.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.flickrKey
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.formatValue
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.methodValue
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.noJsonCallbackValue
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.safeSearchValue
import org.com.raian.flickrcodechallenge.repository.database.FlickerDataBase
import org.com.raian.flickrcodechallenge.repository.database.model.FlickerDataClass
import org.com.raian.flickrcodechallenge.dependency.injection.components.DaggerComponentInjector
import org.com.raian.flickrcodechallenge.dependency.injection.modules.NetworkModule
import org.com.raian.flickrcodechallenge.rest.RestApi
import org.com.raian.flickrcodechallenge.rest.model.FlickrResultApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.logging.Logger
import javax.inject.Inject

class DisplayFetchedDataViewModel(private val db: FlickerDataBase, context: Context) : BaseViewModel() {

    private val listOfDataForUI by lazy {
        MutableLiveData<List<FlickerDataClass>>()
    }

    @Inject
    lateinit var retrofit: Retrofit
    private val injector = DaggerComponentInjector
        .builder()
        .networkModule(NetworkModule())
        .build()
    private lateinit var restApi: RestApi

    init {
        TAG = DisplayFetchedDataViewModel::class.java.simpleName
        logger = Logger.getLogger(TAG)
        inject()
    }

    private fun inject() {
        injector.inject(this)
        restApi = retrofit.create(RestApi::class.java)
    }

    fun checkLocalData(input: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            if (!input.isNullOrEmpty()) {
                clearLocalData()
                input.let {
                    prepareRemoteData(it)
                }
            } else {
                getStoredDataForUI()
            }
        }
    }

    private fun prepareRemoteData(query: String) = GlobalScope.launch(Dispatchers.IO) {
        //Buidling URL
        val queryMap = mutableMapOf(
            "method" to methodValue,
            "api_key" to flickrKey,
            " format" to formatValue,
            "nojsoncallback" to noJsonCallbackValue,
            "safe_search" to safeSearchValue,
            "text" to query
        )

        val deferredDataFetchedResult = async {
            restApi.fetRemoteData(queryMap).enqueue(object : Callback<FlickrResultApi> {
                override fun onFailure(call: Call<FlickrResultApi>, t: Throwable) {
                    logger.severe("$TAG::prepareRemoteData::onFailure::${t.message}::${t.cause}")
                }

                override fun onResponse(call: Call<FlickrResultApi>, response: Response<FlickrResultApi>) {
                    insertDataIntoDb(response)
                }
            })
        }
        deferredDataFetchedResult.await()
    }

    private fun insertDataIntoDb(response: Response<FlickrResultApi>) = GlobalScope.launch {
        withContext(Dispatchers.IO) {
            response.body()?.photos?.photo?.let {
                for (i in it) {
                    with(i) {
                        val innerFlickerDataClass = FlickerDataClass(
                            id?.toLong(),
                            owner,
                            secret,
                            server,
                            farm,
                            title,
                            ispublic.toString(),
                            isfriend,
                            isfamily
                        )
                        db.flickerDao().insert(innerFlickerDataClass)
                    }
                }
            }
            listOfDataForUI.postValue(db.flickerDao().getAll())
        }
    }

    private fun getStoredDataForUI() = GlobalScope.launch(Dispatchers.IO) {
        listOfDataForUI.postValue(db.flickerDao().getAll())
    }

    private fun clearLocalData() = GlobalScope.launch(Dispatchers.IO) {
        db.flickerDao().deleteAll()
    }

    fun getListOfDataForUI(): LiveData<List<FlickerDataClass>> {
        return listOfDataForUI
    }

}