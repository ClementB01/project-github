package fr.appsolute.template.data.networking.api


import fr.appsolute.template.data.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Definition of end points for Character Api
 */
interface UserApi {

    @GET(GET_ALL_GITHUBUSER_PATH)
    suspend fun getAllUsers(): Response<List<GitHubUser>>

    @GET(GET_USER_DETAILS)
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<GitHubUser>


    companion object {
        const val GET_ALL_GITHUBUSER_PATH = "users"
        const val GET_USER_DETAILS = "users/{username}"
    }

}