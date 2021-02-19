package id.ergun.mymoviedb.core.data.remote

import id.ergun.mymoviedb.core.util.Resource
import retrofit2.Response
import timber.log.Timber

/**
 * Created by alfacart on 20/11/20.
 */

fun <T, R> Response<T>.getResult(transform: (T) -> (R)): Resource<R> {
  try {
    val response = this
    if (response.isSuccessful && response.body() != null) {
      val body = transform(response.body()!!)
      if (body != null) {
        return success(body)
      }
    }

    return error("Terjadi kesalahan")
  } catch (e: Exception) {
    return error(e.message ?: e.toString())
  }
}

private fun <T> success(body: T): Resource<T> {
  return Resource.success(body)
}

private fun <T> error(message: String): Resource<T> {
  Timber.e(message)
  return Resource.error(message)
}