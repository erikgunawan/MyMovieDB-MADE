package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.domain.model.Movie
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieRepositoryImpl @Inject constructor(private val localData: MovieDB) : MovieRepository {
    override fun getMovies(): ArrayList<Movie> {
        return Movie.transform(localData)
    }
}