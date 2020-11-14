package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.domain.model.Movie
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieRepositoryImpl @Inject constructor(private val localData: MovieDB) : MovieRepository {
    override fun getMovies(): ArrayList<Movie> {
        val list = arrayListOf<Movie>()
        localData.getDummyMovies().forEach {
            list.add(Movie(
                it.id,
                it.title,
                it.image,
                it.overview,
                it.voteAverage,
                it.tagline
            ))
        }
        return list
    }
}