package com.remitconnect.data.di

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.datasource.country.impl.CountryRemoteDataSourceImpl
import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.datasource.mobilewallet.impl.MobileWalletRemoteDataSourceImpl
import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.datasource.recipient.impl.RecipientRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRecipientRemoteDataSource(
        dataSourceImpl: RecipientRemoteDataSourceImpl
    ): RecipientRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCountryRemoteDataSource(
        dataSourceImpl: CountryRemoteDataSourceImpl
    ): CountryRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMobileWalletRemoteDataSource(
        dataSourceImpl: MobileWalletRemoteDataSourceImpl
    ): MobileWalletRemoteDataSource

}