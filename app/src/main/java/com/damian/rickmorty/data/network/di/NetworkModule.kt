package com.damian.rickmorty.data.network.di

import com.damian.rickmorty.data.network.client.KtorClient
import com.damian.rickmorty.data.network.client.KtorClientAdapter
import com.damian.rickmorty.data.network.service.CharacterApi
import com.damian.rickmorty.data.network.service.CharacterService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Singleton
    @Binds
    abstract fun bindKtorClient(ktorClient: KtorClient): KtorClientAdapter

    @Singleton
    @Binds
    abstract fun provideRickAndMortyService(api: CharacterApi): CharacterService
}