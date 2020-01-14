package rs.hc.android.domain.model.repo

import com.google.gson.annotations.SerializedName

data class Parents(
    @SerializedName("sha") val sha: String,
    @SerializedName("url") val url: String?,
    @SerializedName("html_url") val htmlUrl: String?
)