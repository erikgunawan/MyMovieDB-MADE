package id.ergun.mymoviedb.data

import id.ergun.mymoviedb.data.local.MovieDB
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
                adult = false,
                backdropPath = null,
                genreIds = null,
                id = it.id,
                originalLanguage = null,
                originalTitle = null,
                overview = it.overview,
                popularity = null,
                posterPath = it.posterPath,
                releaseDate = null,
                title = it.title,
                voteAverage = it.voteAverage,
                video = null,
                voteCount = null,
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
            adult = false,
            backdropPath = null,
            genreIds = null,
            id = movie.id,
            originalLanguage = null,
            originalTitle = null,
            overview = movie.overview,
            popularity = null,
            posterPath = movie.posterPath,
            releaseDate = null,
            title = movie.title,
            voteAverage = movie.voteAverage,
            video = null,
            voteCount = null,
            tagLine = movie.tagLine
        )
    }
}