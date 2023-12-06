package com.pinto.githubuser.di

import com.pinto.githubuser.data.repository.GithubUserRepositoryImpl
import com.pinto.githubuser.domain.repository.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindGithubUserRepository(repository: GithubUserRepositoryImpl): GithubUserRepository

}