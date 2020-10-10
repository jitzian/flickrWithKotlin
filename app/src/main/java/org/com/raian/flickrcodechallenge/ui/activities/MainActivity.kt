package org.com.raian.flickrcodechallenge.ui.activities

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.com.raian.flickrcodechallenge.R
import org.com.raian.flickrcodechallenge.constans.GlobalConstants.Companion.totalOfColumns
import org.com.raian.flickrcodechallenge.ui.adapter.RVCustomAdapter
import org.com.raian.flickrcodechallenge.util.NetworkReceiver
import java.util.logging.Logger

class MainActivity : BaseActivity(), NetworkReceiver.NetworkListener  {
    private lateinit var mSearchView: SearchView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var rvCustomAdapter: RVCustomAdapter

    init{
        TAG = MainActivity::class.java.simpleName
        logger = Logger.getLogger(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            setContentView(R.layout.activity_main)
            initViews()
        }
    }

    override fun onResume() {
        super.onResume().also {
            displayFetchedDataViewModel.checkLocalData(mSearchView.query.toString())
        }
    }

    override fun initViews() {
        mSearchView = findViewById(R.id.mSearchView)
        mSearchView.queryHint = getString(R.string.searchHint)
        mRecyclerView = findViewById(R.id.mRecyclerView)
        layoutManager = GridLayoutManager(this, totalOfColumns)
        mRecyclerView.layoutManager = layoutManager
        rvCustomAdapter = RVCustomAdapter(this, displayFetchedDataViewModel.getListOfDataForUI())
        mRecyclerView.adapter = rvCustomAdapter

        setupListeners()
    }

    private fun setupListeners(){
        mSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { displayFetchedDataViewModel.checkLocalData(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun prepareObservers(){
        displayFetchedDataViewModel.getListOfDataForUI().observe(this, Observer{
            rvCustomAdapter.notifyDataSetChanged()
        })
    }

    override fun getNetworkStatus(isConnected: Boolean) {
        try {
            if (!isConnected) {
                logger.severe("$TAG::getNetworkStatus::NO::$isConnected")
                showNoConnectivityMessage()
                prepareObservers()
            } else {
                logger.info("$TAG::getNetworkStatus::YES::$isConnected")
                prepareObservers()
            }
        } catch (rte: RuntimeException) {
            logger.severe("$TAG::getNetworkStatus::${rte.message}")
        }
    }

}
