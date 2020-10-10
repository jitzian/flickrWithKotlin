package org.com.raian.flickrcodechallenge.dependency.injection.components

import dagger.Component
import org.com.raian.flickrcodechallenge.dependency.injection.modules.NetworkModule
import org.com.raian.flickrcodechallenge.showPhotos.viewmodel.DisplayFetchedDataViewModel

@Component(modules = [NetworkModule::class])
interface ComponentInjector{
    fun inject(displayFetchedDataViewModel: DisplayFetchedDataViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ComponentInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}