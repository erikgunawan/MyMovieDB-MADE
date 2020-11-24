package id.ergun.mymoviedb.domain.usecase.movie

import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getMovies(): Resource<ArrayList<Movie>> {
        return repository.getMovies()
    }

    override suspend fun getMovieDetail(id: Int): Resource<Movie> {
        return repository.getMovieDetail(id)
    }
}