package org.com.raian.flickrcodechallenge.showPhotos.viewmodel

import androidx.lifecycle.ViewModel
import java.util.logging.Logger

abstract class BaseViewModel : ViewModel() {
    internal lateinit var TAG: String
    internal lateinit var logger: Logger
}