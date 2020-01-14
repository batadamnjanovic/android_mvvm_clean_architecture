package rs.hc.android.domain

import rs.hc.android.data.repository.AppRepository
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.domain.model.user.UserRepo

class UserDetailsUseCase(private val repository: AppRepository) {

    suspend fun getUserDetails(username: String): Result<UserDetails?> =
        repository.getUserDetails(username)

    suspend fun getUserRepos(username: String): Result<List<UserRepo>?> =
        repository.getUserRepos(username)

}