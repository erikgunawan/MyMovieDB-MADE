package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface MovieRepository {
    suspend fun getMovies(): Resource<ArrayList<Movie>>
    suspend fun getMovieDetail(id: Int): Resource<Movie>
}