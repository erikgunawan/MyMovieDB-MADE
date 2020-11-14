package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel @ViewModelInject constructor(private val useCase: MovieUseCase) : ViewModel() {

    lateinit var movie: Movie

    fun setSelectedMovie(movie: Movie) {
        this.movie = movie
    }

    fun getMovies() = useCase.getMovies()
}