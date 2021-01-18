package id.ergun.mymoviedb.core.domain.usecase.movie

import id.ergun.mymoviedb.core.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.domain.model.Movie
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getMovies(page: Int): Resource<ArrayList<Movie>> {
        return repository.getMovies(page)
    }

    override suspend fun getMovieDetail(id: Int): Resource<Movie> {
        return repository.getMovieDetail(id)
    }

    override suspend fun getFavoriteMovies(): Resource<ArrayList<Movie>> {
        return repository.getFavoriteMovies()
    }

    override suspend fun getFavoriteMovie(id: Int): Resource<Movie> {
        return repository.getFavoriteMovie(id)
    }

    override suspend fun addToFavorite(movie: Movie): Long {
        return repository.addToFavorite(movie)
    }

    override suspend fun removeFromFavorite(id: Int): Int {
        return repository.removeFromFavorite(id)
    }

}