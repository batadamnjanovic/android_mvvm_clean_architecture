package rs.hc.android.domain

import rs.hc.android.data.repository.AppRepository
import rs.hc.android.domain.model.repo.BaseCommit

class RepoUseCase(private val repository: AppRepository) {

    suspend fun getCommits(owner: String, repoName: String): Result<List<BaseCommit>?> =
        repository.getCommitDetails(owner, repoName)

}