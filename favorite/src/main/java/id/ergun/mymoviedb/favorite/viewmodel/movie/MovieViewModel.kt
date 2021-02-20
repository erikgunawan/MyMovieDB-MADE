package id.ergun.mymoviedb.favorite.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class MovieViewModel(
    private val movieDataSourceFactory: MovieDataSourceFactory,
    private val tvShowDataSourceFactory: TvShowDataSourceFactory
) : ViewModel() {

  var pageType = Const.MOVIE_TYPE

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  fun setFavorite(favoritePage: Boolean) {
    this.favoritePage = favoritePage
    if (pageType == Const.MOVIE_TYPE)
      movieDataSourceFactory.favoritePage = favoritePage
    else
      tvShowDataSourceFactory.favoritePage = favoritePage
  }

  fun getMovies(): LiveData<PagedList<MovieVR>> =
      if (pageType == Const.MOVIE_TYPE)
        movieDataSourceFactory.getMovies()
      else
        tvShowDataSourceFactory.getTvShows()

  val movieState: LiveData<Resource<*>> =
      if (pageType == Const.MOVIE_TYPE)
        Transformations.switchMap(
            movieDataSourceFactory.liveData,
            MovieKeyedDataSource::state
        )
      else
        Transformations.switchMap(
            tvShowDataSourceFactory.liveData,
            TvShowKeyedDataSource::state
        )

  fun refresh() {
    if (pageType == Const.MOVIE_TYPE)
      movieDataSourceFactory.liveData.value?.invalidate()
    else
      tvShowDataSourceFactory.liveData.value?.invalidate()
  }
}