package rs.hc.android.domain.model.user

import com.google.gson.annotations.SerializedName

data class UserRepo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val private: Boolean,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("commits_url") val commitsUrl: String,
    @SerializedName("git_commits_url") val gitCommitsUrl: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("git_url") val gitUrl: String,
    @SerializedName("size") val size: Int,
    @SerializedName("language") val language: String,
    @SerializedName("disabled") val disabled: Boolean,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("open_issues") val openIssues: Int
)