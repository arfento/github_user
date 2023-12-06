package com.pinto.githubuser.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinto.githubuser.data.models.GithubRepositoryModel
import com.pinto.githubuser.domain.repository.GithubUserRepository
import com.pinto.githubuser.utils.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: GithubUserRepository,
) : ViewModel() {


    private val _repository = MutableLiveData<SuccessResult<List<GithubRepositoryModel>>>()

    val repository: LiveData<SuccessResult<List<GithubRepositoryModel>>>
        get() = _repository

    init {
        getAllGithubUsers()
    }

    private fun getAllGithubUsers(){
        viewModelScope.launch {
            userRepository.getRepo().let {
                if (it.isSuccessful){
                    _repository.postValue(SuccessResult.success(it.body()))
                }else{
                    _repository.postValue(SuccessResult.error(it.errorBody().toString(), null))
                }
            }
        }
    }

}