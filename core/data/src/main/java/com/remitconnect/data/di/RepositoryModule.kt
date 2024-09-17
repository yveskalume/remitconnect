package com.remitconnect.data.di

import com.remitconnect.data.repository.CountryRepositoryImpl
import com.remitconnect.data.repository.MobileWalletRepositoryImpl
import com.remitconnect.data.repository.RecipientRepositoryImpl
import com.remitconnect.domain.repository.CountryRepository
import com.remitconnect.domain.repository.MobileWalletRepository
import com.remitconnect.domain.repository.RecipientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipientRepository(
        repositoryImpl: RecipientRepositoryImpl
    ): RecipientRepository

    @Binds
    @Singleton
    abstract fun bindCountryRepository(
        repositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    @Binds
    @Singleton
    abstract fun bindMobileWalletRepository(
        repositoryImpl: MobileWalletRepositoryImpl
    ): MobileWalletRepository

}