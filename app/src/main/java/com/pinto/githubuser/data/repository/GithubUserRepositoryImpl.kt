package com.pinto.githubuser.data.repository

import com.pinto.githubuser.data.network.ApiEndPoint
import com.pinto.githubuser.domain.repository.GithubUserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepositoryImpl @Inject constructor(
    private val apiService: ApiEndPoint,
) : GithubUserRepository {
    override suspend fun getRepo() = apiService.getAllRepo()
}