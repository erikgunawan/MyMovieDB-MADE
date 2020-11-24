package id.ergun.mymoviedb.ui.view.movie

import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
data class MovieVR(
    val id: Int?,
    val title: String,
    val image: Int?,
    var overview: String,
    val voteAverage: Double,
    val tagLine: String,
    val posterPath: String
) {

    companion object {
        private fun fromModel(movie: Movie): MovieVR {
            return MovieVR(
                movie.id,
                movie.title,
                movie.image,
                movie.overview,
                movie.voteAverage,
                movie.tagLine,
                movie.posterPath
            )
        }

        fun toModel(movieVR: MovieVR): Movie {
            return Movie(
                movieVR.id,
                movieVR.title,
                movieVR.image,
                movieVR.overview,
                movieVR.voteAverage,
                movieVR.tagLine,
                movieVR.posterPath
            )
        }

        fun transform(list: ArrayList<Movie>): ArrayList<MovieVR> {
            val newList = arrayListOf<MovieVR>()
            list.forEach {
                newList.add(fromModel(it))
            }

            return newList
        }
    }
}