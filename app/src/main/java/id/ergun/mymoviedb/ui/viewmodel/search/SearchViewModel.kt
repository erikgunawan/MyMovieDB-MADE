package id.ergun.mymoviedb.ui.viewmodel.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.movie.search.MovieSearchDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.movie.search.MovieSearchKeyedDataSource
import id.ergun.mymoviedb.ui.datasource.tvshow.search.TvShowSearchDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.search.TvShowSearchKeyedDataSource

/**
 * Created by root on 20/02/21.
 */
class SearchViewModel @ViewModelInject constructor(
    private val movieDataSourceFactory: MovieSearchDataSourceFactory,
    private val tvShowDataSourceFactory: TvShowSearchDataSourceFactory
) : ViewModel() {

  var pageType: Int = Const.MOVIE_TYPE

  fun search(query: String): LiveData<PagedList<MovieVR>> =
      if (pageType == Const.MOVIE_TYPE)
        movieDataSourceFactory.searchMovie(query)
      else
        tvShowDataSourceFactory.searchTvShow(query)

  private val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          movieDataSourceFactory.liveData,
          MovieSearchKeyedDataSource::state
      )

  private val tvShowState: LiveData<Resource<*>> =
      Transformations.switchMap(
          tvShowDataSourceFactory.liveData,
          TvShowSearchKeyedDataSource::state
      )

  fun getState(): LiveData<Resource<*>> =
      if (pageType == Const.MOVIE_TYPE)
        movieState
      else
        tvShowState

  fun refresh() {
    if (pageType == Const.MOVIE_TYPE)
      movieDataSourceFactory.liveData.value?.invalidate()
    else
      tvShowDataSourceFactory.liveData.value?.invalidate()
  }
}