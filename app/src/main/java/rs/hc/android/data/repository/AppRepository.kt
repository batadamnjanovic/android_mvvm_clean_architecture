package rs.hc.android.data.repository

import rs.hc.android.domain.model.repo.BaseCommit
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.domain.model.user.UserRepo

interface AppRepository {

    suspend fun getUserDetails(username: String): Result<UserDetails?>

    suspend fun getUserRepos(username: String): Result<List<UserRepo>?>

    suspend fun getCommitDetails(owner: String, repoName: String): Result<List<BaseCommit>?>

}