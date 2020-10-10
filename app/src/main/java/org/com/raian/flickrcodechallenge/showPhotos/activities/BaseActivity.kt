package org.com.raian.flickrcodechallenge.showPhotos.activities

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import org.com.raian.flickrcodechallenge.R
import org.com.raian.flickrcodechallenge.showPhotos.viewmodel.DisplayFetchedDataViewModel
import org.com.raian.flickrcodechallenge.ui.factories.FactoryViewModel
import org.com.raian.flickrcodechallenge.util.NetworkReceiver
import java.util.logging.Logger

abstract class BaseActivity : AppCompatActivity(), NetworkReceiver.NetworkListener {
    internal lateinit var TAG: String
    internal lateinit var logger: Logger
    private lateinit var mSnackBar: Snackbar
    private lateinit var networkReceiver: NetworkReceiver

    internal val displayFetchedDataViewModel by lazy {
        ViewModelProvider(this, FactoryViewModel(this))
            .get(DisplayFetchedDataViewModel::class.java)
    }

    override fun onStart() {
        super.onStart().also {
            networkReceiver = NetworkReceiver(this)
            registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onStop() {
        super.onStop().also {
            unregisterReceiver(networkReceiver)
        }
    }

    fun showNoConnectivityMessage() {
        mSnackBar = Snackbar.make(
            findViewById(R.id.mLayoutMainActivityContainer),
            getString(R.string.NoConnectivityMessage),
            Snackbar.LENGTH_LONG
        ) //Assume "rootLayout" as the root layout of every activity.
        mSnackBar.show()
    }

    abstract fun initViews()

    override fun onDestroy() {
        super.onDestroy().also { viewModelStore.clear() }
    }

}