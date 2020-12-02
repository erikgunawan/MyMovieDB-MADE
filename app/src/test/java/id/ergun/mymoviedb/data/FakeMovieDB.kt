package id.ergun.mymoviedb.data

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.model.MovieLocal
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 23/11/20.
 */
object FakeMovieDB {

    fun getMovies(localData: MovieDB): Resource<ArrayList<Movie>> {
        return Resource(
            data = Movie.transform(localData),
            status = Resource.Status.SUCCESS,
            message = null
        )
    }

    fun getMovieDetail(movie: Movie): Resource<Movie> {
        return Resource(
            data = movie,
            status = Resource.Status.SUCCESS,
            message = null
        )
    }

    fun getMoviesRemote(localData: MovieDB): MovieResponse {
        val data = mutableListOf<MovieResponse.Result>()

        localData.getMovies().forEach {
            data.add(MovieResponse.Result(
                id = it.id,
                overview = it.overview,
                posterPath = it.posterPath,
                title = it.title,
                voteAverage = it.voteAverage,
                tagLine = it.tagLine
            ))
        }
        return MovieResponse(
            page = 1,
            totalPages = 10,
            totalResults = 10,
            results = data
        )
    }

    fun getMovieDetailRemote(movie: Movie): MovieResponse.Result {
        return MovieResponse.Result(
            id = movie.id,
            overview = movie.overview,
            posterPath = movie.posterPath,
            title = movie.title,
            voteAverage = movie.voteAverage,
            tagLine = movie.tagLine
        )
    }

    fun getMoviesLocal(localData: MovieDB): MutableList<MovieLocal> {
        val data = mutableListOf<MovieLocal>()
        localData.getMovies().forEach {
            data.add(
                MovieLocal(
                    id = it.id,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    title = it.title,
                    voteAverage = it.voteAverage,
                    tagLine = it.tagLine
                )
            )
        }
        return data
    }

    fun getMovieDetailLocal(movie: Movie): MovieLocal {
        return MovieLocal(
            id = movie.id ?: 0,
            overview = movie.overview,
            posterPath = movie.posterPath,
            title = movie.title,
            voteAverage = movie.voteAverage,
            tagLine = movie.tagLine
        )
    }
}