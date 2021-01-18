package id.ergun.mymoviedb.core.domain.usecase.movie

import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface MovieUseCase {
    suspend fun getMovies(page: Int): Resource<ArrayList<Movie>>
    suspend fun getMovieDetail(id: Int): Resource<Movie>
    suspend fun getFavoriteMovies(): Resource<ArrayList<Movie>>
    suspend fun getFavoriteMovie(id: Int): Resource<Movie>
    suspend fun addToFavorite(movie: Movie): Long
    suspend fun removeFromFavorite(id: Int): Int
}