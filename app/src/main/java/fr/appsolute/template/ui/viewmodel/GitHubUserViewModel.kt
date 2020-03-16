package fr.appsolute.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.data.repository.GitHubUserRepository
import kotlinx.coroutines.launch

open class GitHubUserViewModel(
    private  val repository: GitHubUserRepository
) :ViewModel() {

    fun getAllGitHubUser(onSuccess: OnSuccess<List<GitHubUser>>){
        viewModelScope.launch {
            repository.getListUser()?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GitHubUserViewModel(GitHubUserRepository.instance) as T
        }
    }
}