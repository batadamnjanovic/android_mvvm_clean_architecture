package rs.hc.android.data.repository

import kotlinx.coroutines.suspendCancellableCoroutine
import rs.hc.android.data.remote.GithubAPI
import rs.hc.android.domain.model.repo.BaseCommit
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.domain.model.user.UserRepo
import rs.hc.android.extension.enqueue
import kotlin.coroutines.resume

class AppRepositoryImpl(private val githubApi: GithubAPI) : AppRepository {

    /**
     * Get user details.
     */
    override suspend fun getUserDetails(username: String): Result<UserDetails?> {
        return suspendCancellableCoroutine { continuation ->
            githubApi.getUserDetails(username).enqueue { result ->
                continuation.resume(result)
            }
        }
    }

    /**
     * Get the list of all repositories that belongs to a user.
     */
    override suspend fun getUserRepos(username: String): Result<List<UserRepo>?> {
        return suspendCancellableCoroutine { continuation ->
            githubApi.getUserRepos(username).enqueue { result ->
                continuation.resume(result)
            }
        }
    }

    /**
     * Get the list of all commits.
     */
    override suspend fun getCommitDetails(
        owner: String,
        repoName: String
    ): Result<List<BaseCommit>?> {
        return suspendCancellableCoroutine { continuation ->
            githubApi.getCommitDetails(owner, repoName).enqueue { result ->
                continuation.resume(result)
            }
        }
    }

}
