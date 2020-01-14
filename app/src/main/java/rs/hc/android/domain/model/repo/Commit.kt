package rs.hc.android.domain.model.repo

import com.google.gson.annotations.SerializedName

data class Commit (
    @SerializedName("author") val author : CommitAuthor,
    @SerializedName("committer") val committer : CommitCommitter,
    @SerializedName("message") val message : String?,
    @SerializedName("tree") val tree : Tree?,
    @SerializedName("url") val url : String?,
    @SerializedName("comment_count") val commentCount : Int?,
    @SerializedName("verification") val verification : Verification
)