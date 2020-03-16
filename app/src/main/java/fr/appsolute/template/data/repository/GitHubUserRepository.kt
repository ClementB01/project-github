package fr.appsolute.template.data.repository

import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.data.networking.HttpClientManager
import fr.appsolute.template.data.networking.api.GitHubUserApi
import fr.appsolute.template.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class GitHubUserRepositoryImpl(
    private  val api: GitHubUserApi
):
    GitHubUserRepository {
    override suspend fun getListUser(): List<GitHubUser>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllCharacter()
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body() ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

interface GitHubUserRepository {

    suspend fun getListUser(): List<GitHubUser>?

    companion object {
        val instance: GitHubUserRepository by lazy {
            GitHubUserRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}