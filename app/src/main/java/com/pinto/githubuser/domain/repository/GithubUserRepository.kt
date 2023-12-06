package com.pinto.githubuser.domain.repository

import com.pinto.githubuser.data.models.GithubRepositoryModel
import retrofit2.Response

interface GithubUserRepository {
     suspend fun getRepo() : Response<List<GithubRepositoryModel>>
}