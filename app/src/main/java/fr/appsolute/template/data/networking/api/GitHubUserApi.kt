package fr.appsolute.template.data.networking.api


import fr.appsolute.template.data.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Definition of end points for Character Api
 */
interface GitHubUserApi {

    @GET(GET_ALL_GITHUBUSER_PATH)
    suspend fun getAllCharacter(): Response<List<GitHubUser>>


    companion object {
        const val GET_ALL_GITHUBUSER_PATH = "users"
    }

}