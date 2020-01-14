package rs.hc.android.mapper

import rs.hc.android.domain.model.repo.BaseCommit
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.domain.model.user.UserRepo
import rs.hc.android.ui.commitdetails.datamodel.CommitsDataModel
import rs.hc.android.ui.userdetails.datamodel.UserDetailsDataModel
import rs.hc.android.ui.userrepos.datamodel.UserRepoDataModel

class DataModelMapper {

    fun mapToUserDetailsDataModel(userDetails: UserDetails): UserDetailsDataModel {
        return UserDetailsDataModel(userDetails.name, userDetails.company, userDetails.avatarUrl)
    }

    fun mapToUserReposDataModel(userRepos: List<UserRepo>): MutableList<UserRepoDataModel> {
        val reposDataModel: MutableList<UserRepoDataModel> = mutableListOf()

        userRepos.forEach {
            reposDataModel.add(mapToUserRepoDataModel(it))
        }

        return reposDataModel
    }

    fun mapToCommitsDataModel(commits: List<BaseCommit>): MutableList<CommitsDataModel> {
        val commitsDataModel: MutableList<CommitsDataModel> = mutableListOf()

        commits.forEach {
            commitsDataModel.add(mapToCommitDataModel(it))
        }

        return commitsDataModel
    }

    private fun mapToUserRepoDataModel(userRepo: UserRepo): UserRepoDataModel {
        return UserRepoDataModel(userRepo.id, userRepo.name, userRepo.openIssuesCount)
    }

    private fun mapToCommitDataModel(baseCommit: BaseCommit): CommitsDataModel {
        return CommitsDataModel(
            baseCommit.sha,
            baseCommit.commit.message,
            baseCommit.commit.author.name,
            baseCommit.commit.author.email,
            baseCommit.commit.author.date,
            baseCommit.commit.committer.name,
            baseCommit.commit.committer.email,
            baseCommit.commit.committer.date
        )
    }
}
