package rs.hc.android.extension

import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rs.hc.android.data.remote.model.APIError
import java.io.IOException


/**
 * Extension function that handles success and failure of an API request.
 */
inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T?>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            when (response.code()) {

                // Success
                in 200..299 -> result(Result.success(response.body()))

                401 -> result(Result.failure(Error("Unauthenticated")))

                in 400..499 -> {
                    // TODO Parse error body
                    result(Result.failure(Error("Client error")))
                }

                in 500..599 -> result(Result.failure(Error("Server error")))

                // Unknown error
                else -> result(Result.failure(APIError(-1, response.message())))
            }
        }

        override fun onFailure(call: Call<T>, e: Throwable) {
            when (e) {
                is IOException -> result(Result.failure(Error("Network error")))
                else -> result(Result.failure(APIError(-1, e.localizedMessage ?: "Unknown error")))
            }
        }
    })
}



fun AppCompatActivity.doSomethign(){

}