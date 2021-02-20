package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.FavoriteModel.Type
import id.ergun.mymoviedb.core.util.Resource

/**
 * Created by root on 21/02/21.
 */

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

  var pageType = Const.MOVIE_TYPE

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  var favoriteState: MutableLiveData<Type> = MutableLiveData()

  lateinit var movie: Movie

  fun setSelectedMovie(movie: Movie) {
    this.movie = movie
  }

  fun getMovieDetail(id: Int) =
      if (pageType == Const.MOVIE_TYPE)
        liveData { emit(movieUseCase.getMovieDetail(id)) }
      else
        liveData { emit(tvShowUseCase.getTvShowDetail(id)) }

  private fun getFavoriteMovie(id: Int) =
      if (pageType == Const.MOVIE_TYPE)
        liveData { emit(movieUseCase.getFavoriteMovie(id)) }
      else
        liveData { emit(tvShowUseCase.getFavoriteTvShow(id)) }

  fun getFavoriteMovieById(): LiveData<Resource<Movie>> =
      if (movie.id != null)
        getFavoriteMovie(movie.id!!)
      else
        liveData { }

  fun addToFavorite(movie: Movie): LiveData<Long> =
      if (pageType == Const.MOVIE_TYPE)
        liveData { emit(movieUseCase.addToFavorite(movie)) }
      else
        liveData { emit(tvShowUseCase.addToFavorite(movie)) }

  fun removeFromFavorite(id: Int): LiveData<Int> =
      if (pageType == Const.MOVIE_TYPE)
        liveData { emit(movieUseCase.removeFromFavorite(id)) }
      else
        liveData { emit(tvShowUseCase.removeFromFavorite(id)) }
}