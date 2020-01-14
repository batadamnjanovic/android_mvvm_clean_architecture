package rs.hc.android.data.remote

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercepts the HTTP request.
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url().newBuilder().build()

        req = req.newBuilder()
            .url(url)
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()

        return chain.proceed(req)
    }
}
