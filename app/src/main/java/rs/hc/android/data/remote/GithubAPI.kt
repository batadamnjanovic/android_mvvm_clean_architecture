package rs.hc.android.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rs.hc.android.domain.model.repo.BaseCommit
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.domain.model.user.UserRepo

interface GithubAPI {

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Call<UserDetails>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<UserRepo>>

    @GET("repos/{owner}/{name}/commits")
    fun getCommitDetails(@Path("owner") owner: String, @Path("name") repoName: String): Call<List<BaseCommit>>

}