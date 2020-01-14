package rs.hc.android.ui.commitdetails.datamodel

class CommitsDataModel(
    val shaId: String,
    val message: String?,
    val authorName: String,
    val authorEmail: String,
    val authorDate: String,
    val committerName: String,
    val committerEmail: String,
    val committerDate: String
)
