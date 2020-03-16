package fr.appsolute.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.data.repository.UserRepository
import kotlinx.coroutines.launch

open class UserViewModel(
    private  val repository: UserRepository
) :ViewModel() {

    fun getAllUsers(onSuccess: OnSuccess<List<GitHubUser>>){
        viewModelScope.launch {
            repository.getListUser()?.run(onSuccess)
        }
    }

    fun getUserDetails(username: String, onSuccess: OnSuccess<GitHubUser>){
        viewModelScope.launch {
            repository.getUserDetail(username)?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(UserRepository.instance) as T
        }
    }
}