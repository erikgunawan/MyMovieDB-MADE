package id.ergun.mymoviedb.core.util

/**
 * Created by alfacart on 20/11/20.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

  enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY_DATA
  }

  companion object {
    fun <T> success(data: T): Resource<T> {
      return Resource(Status.SUCCESS, data, null)
    }

    fun <T> emptyData(message: String, data: T? = null): Resource<T> {
      return Resource(Status.EMPTY_DATA, data, message)
    }

    fun <T> error(message: String, data: T? = null): Resource<T> {
      return Resource(Status.ERROR, data, message)
    }

    fun <T> loading(data: T? = null): Resource<T> {
      return Resource(Status.LOADING, data, null)
    }
  }
}