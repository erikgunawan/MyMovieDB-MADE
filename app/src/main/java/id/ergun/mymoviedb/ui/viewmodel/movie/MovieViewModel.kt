package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.ui.view.favorite.FavoriteModel
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel @ViewModelInject constructor(private val useCase: MovieUseCase) : ViewModel() {

    var favoritePage: Boolean = false

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    lateinit var movie: Movie

    fun setSelectedMovie(movie: Movie) {
        this.movie = movie
    }

    fun getMovies() = liveData {
        if (favoritePage) emit(useCase.getFavoriteMovies())
        else emit(useCase.getMovies()) }

    fun getMovieDetail(id: Int): LiveData<Resource<Movie>> {
        return liveData { emit(useCase.getMovieDetail(id)) }
    }

    private fun getFavoriteMovie(id: Int) = liveData { emit(useCase.getFavoriteMovie(id)) }

    fun getFavoriteMovieById() = if (movie.id != null) getFavoriteMovie(movie.id!!)
    else liveData {  }

    fun addToFavorite(movie: Movie) = liveData {  emit(useCase.addToFavorite(movie)) }

    fun removeFromFavorite(id: Int) = liveData { emit(useCase.removeFromFavorite(id)) }
}