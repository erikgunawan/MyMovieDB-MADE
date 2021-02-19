package id.ergun.mymoviedb.ui.datasource.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.view.movie.MovieVR
import javax.inject.Inject

/**
 * Created by root on 20/02/21.
 */
class MovieSearchDataSourceFactory @Inject constructor(
    private val useCase: MovieUseCase
) : DataSource.Factory<Int, MovieVR>() {

  val liveData: MutableLiveData<MovieSearchKeyedDataSource> = MutableLiveData<MovieSearchKeyedDataSource>()

  private var query = ""
  var favoritePage: Boolean = false

  override fun create(): DataSource<Int, MovieVR> {
    val source = MovieSearchKeyedDataSource(useCase)
    source.favoritePage = favoritePage
    source.query = query
    liveData.postValue(source)
    return source
  }

  fun searchMovie(query: String): LiveData<PagedList<MovieVR>> {
    this.query = query
    return LivePagedListBuilder(this, pagedListConfig()).build()
  }

  companion object {
    private const val PAGE_SIZE = 10

    fun pagedListConfig(): PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(true)
        .build()
  }
}