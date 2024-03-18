package com.damian.rickmorty.data.repository.di

import com.damian.rickmorty.data.repository.character.CharacterRepositoryImpl
import com.damian.rickmorty.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRickAndMortyRepository(repo: CharacterRepositoryImpl): CharacterRepository
}