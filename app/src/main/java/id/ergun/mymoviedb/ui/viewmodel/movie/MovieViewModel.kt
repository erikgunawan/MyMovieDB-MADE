package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.movie.MovieKeyedDataSource
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel @ViewModelInject constructor(
    private val movieDataSourceFactory: MovieDataSourceFactory,
    private val tvShowDataSourceFactory: TvShowDataSourceFactory
) : ViewModel() {

  var pageType: Int = Const.MOVIE_TYPE

  fun getMovies(): LiveData<PagedList<MovieVR>> =
      if (pageType == Const.MOVIE_TYPE)
        movieDataSourceFactory.getMovies()
      else
        tvShowDataSourceFactory.getTvShows()

  private val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          movieDataSourceFactory.liveData,
          MovieKeyedDataSource::state
      )

  private val tvShowState: LiveData<Resource<*>> =
      Transformations.switchMap(
          tvShowDataSourceFactory.liveData,
          TvShowKeyedDataSource::state
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