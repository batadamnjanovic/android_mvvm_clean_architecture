package rs.hc.android.data.remote.model

data class APIError(val code: Int, val value: String?) : Error(value)