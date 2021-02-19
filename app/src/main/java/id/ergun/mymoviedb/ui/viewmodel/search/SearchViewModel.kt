package id.ergun.mymoviedb.ui.viewmodel.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.search.MovieSearchDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.search.MovieSearchKeyedDataSource

/**
 * Created by root on 20/02/21.
 */
class SearchViewModel @ViewModelInject constructor(
    private val dataSourceFactory: MovieSearchDataSourceFactory
) : ViewModel() {

  fun searchMovie(query: String): LiveData<PagedList<MovieVR>> = dataSourceFactory.searchMovie(
      query)

  val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          dataSourceFactory.liveData,
          MovieSearchKeyedDataSource::state
      )

  fun refresh() {
    dataSourceFactory.liveData.value?.invalidate()
  }
}