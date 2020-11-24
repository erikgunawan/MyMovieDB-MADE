package id.ergun.mymoviedb.data.remote

import id.ergun.mymoviedb.util.Resource
import id.ergun.mymoviedb.util.testing.EspressoIdlingResource
import retrofit2.Response
import timber.log.Timber

/**
 * Created by alfacart on 20/11/20.
 */

fun <T, R> Response<T>.getResult(transform: (T) -> (R)): Resource<R> {
    try {
        EspressoIdlingResource.increment()
        val response = this
        if (response.isSuccessful) {
            val body = transform(response.body()!!)
            if (body != null) {
                EspressoIdlingResource.decrement()
                return Resource.success(body)
            }
        }

        return error("Terjadi kesalahan")
    } catch (e: Exception) {
        return error(e.message ?: e.toString())
    }
}

private fun <T> error(message: String): Resource<T> {
    EspressoIdlingResource.decrement()
    Timber.e(message)
    return Resource.error(message)
}