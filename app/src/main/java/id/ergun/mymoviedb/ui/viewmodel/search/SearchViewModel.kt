package id.ergun.mymoviedb.ui.viewmodel.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.core.view.tvshow.TvShowVR
import id.ergun.mymoviedb.ui.datasource.search.MovieSearchDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.search.MovieSearchKeyedDataSource
import id.ergun.mymoviedb.ui.datasource.search.TvShowSearchDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.search.TvShowSearchKeyedDataSource

/**
 * Created by root on 20/02/21.
 */
class SearchViewModel @ViewModelInject constructor(
    private val movieDataSourceFactory: MovieSearchDataSourceFactory,
    private val tvShowDataSourceFactory: TvShowSearchDataSourceFactory
) : ViewModel() {

  var pageType: Int = Const.MOVIE_TYPE

  fun searchMovie(query: String): LiveData<PagedList<MovieVR>> = movieDataSourceFactory.searchMovie(
      query)

  fun searchTvShow(query: String): LiveData<PagedList<TvShowVR>> = tvShowDataSourceFactory.searchTvShow(
      query)

  val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          movieDataSourceFactory.liveData,
          MovieSearchKeyedDataSource::state
      )

  val tvShowState: LiveData<Resource<*>> =
      Transformations.switchMap(
          tvShowDataSourceFactory.liveData,
          TvShowSearchKeyedDataSource::state
      )

  val state = if (pageType == Const.MOVIE_TYPE) movieState else tvShowState

  fun refresh() {
    movieDataSourceFactory.liveData.value?.invalidate()
  }
}