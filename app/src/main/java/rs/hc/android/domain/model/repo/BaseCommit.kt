package rs.hc.android.domain.model.repo

import com.google.gson.annotations.SerializedName

data class BaseCommit (
	@SerializedName("sha") val sha : String,
	@SerializedName("node_id") val nodeId : String,
	@SerializedName("commit") val commit : Commit,
	@SerializedName("url") val url : String?,
	@SerializedName("html_url") val htmlUrl : String?,
	@SerializedName("comments_url") val commentsUrl : String?,
	@SerializedName("author") val author : Author?,
	@SerializedName("committer") val committer : Committer?,
	@SerializedName("parents") val parents : List<Parents>?
)