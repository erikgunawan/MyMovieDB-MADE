package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.util.FavoriteModel
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.movie.MovieKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel @ViewModelInject constructor(
    private val useCase: MovieUseCase,
    private val dataSourceFactory: MovieDataSourceFactory
) : ViewModel() {

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

  lateinit var movie: Movie

  fun setFavorite(favoritePage: Boolean) {
    this.favoritePage = favoritePage
    dataSourceFactory.favoritePage = favoritePage
  }

  fun setSelectedMovie(movie: Movie) {
    this.movie = movie
  }

  fun getMovies(): LiveData<PagedList<MovieVR>> = dataSourceFactory.getMovies()

  val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          dataSourceFactory.liveData,
          MovieKeyedDataSource::state
      )

  fun refresh() {
    dataSourceFactory.liveData.value?.invalidate()
  }

  fun getMovieDetail(id: Int): LiveData<Resource<Movie>> {
    return liveData { emit(useCase.getMovieDetail(id)) }
  }

  private fun getFavoriteMovie(id: Int) = liveData { emit(useCase.getFavoriteMovie(id)) }

  fun getFavoriteMovieById(): LiveData<Resource<Movie>> =
      if (movie.id != null) getFavoriteMovie(movie.id!!)
      else liveData { }

  fun addToFavorite(movie: Movie): LiveData<Long> =
      liveData { emit(useCase.addToFavorite(movie)) }

  fun removeFromFavorite(id: Int): LiveData<Int> =
      liveData { emit(useCase.removeFromFavorite(id)) }
}