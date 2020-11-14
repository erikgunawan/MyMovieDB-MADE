package id.ergun.mymoviedb.domain.usecase.movie

import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
interface MovieUseCase {
    fun getMovies(): ArrayList<Movie>
}