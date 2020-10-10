package org.com.raian.flickrcodechallenge.dependency.injection.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.com.raian.flickrcodechallenge.constans.GlobalConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun providesNetworkModule(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GlobalConstants.baseURL)
            .build()
    }
}