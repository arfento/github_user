package com.pinto.githubuser.data.network

import com.pinto.githubuser.data.models.GithubRepositoryModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("repositories")
    suspend fun getAllRepo() : Response<List<GithubRepositoryModel>>
}