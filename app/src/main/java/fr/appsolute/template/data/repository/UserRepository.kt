package fr.appsolute.template.data.repository

import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.data.networking.HttpClientManager
import fr.appsolute.template.data.networking.api.UserApi
import fr.appsolute.template.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class UserRepositoryImpl(
    private  val api: UserApi
): UserRepository {
    override suspend fun getListUser(): List<GitHubUser>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllUsers()
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body() ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun getUserDetail(username: String): GitHubUser? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getUserDetails(username)
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body() ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

interface UserRepository {

    suspend fun getListUser(): List<GitHubUser>?

    suspend fun getUserDetail(username: String): GitHubUser?

    companion object {
        val instance: UserRepository by lazy {
            UserRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}