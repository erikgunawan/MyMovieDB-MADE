package id.ergun.mymoviedb.ui.datasource.movie.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.core.data.Const
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by root on 20/02/21.
 */
class MovieSearchKeyedDataSource(
    private val useCase: MovieUseCase
) : PageKeyedDataSource<Int, MovieVR>() {

  var state: MutableLiveData<Resource<*>> = MutableLiveData()

  var query: String = ""

  private val job = Job()
  private val scope = CoroutineScope(Dispatchers.IO + job)

  override fun loadInitial(
      params: LoadInitialParams<Int>,
      callback: LoadInitialCallback<Int, MovieVR>
  ) {
    state.postValue(Resource.loading(null))
    scope.launch {
      try {
        val response = useCase.searchMovie(query = query, page = Const.INITIAL_PAGE)
        if (response.status == Resource.Status.SUCCESS && !response.data.isNullOrEmpty()) {
          state.postValue(response)
          val items = MovieVR.transform(response.data ?: arrayListOf()).toMutableList()
          callback.onResult(items, null, 2)
          return@launch
        }
        state.postValue(Resource.emptyData("Movie yang Anda cari tidak ditemukan", null))
      } catch (exception: Exception) {
        Timber.e(exception)
        state.postValue(Resource.error("Terjadi kesalahan", data = null))
      }
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieVR>) {
    // not implemented
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieVR>) {
    scope.launch {
      try {
        val response = useCase.searchMovie(query = query, page = params.key)
        if (response.status == Resource.Status.SUCCESS) {
          state.postValue(response)
          val items = MovieVR.transform(response.data ?: return@launch).toMutableList()
          callback.onResult(items, params.key + 1)
        }
      } catch (exception: Exception) {
        Timber.e(exception)
      }
    }
  }

  override fun invalidate() {
    super.invalidate()
    job.cancel()
  }

}
