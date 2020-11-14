package id.ergun.mymoviedb.ui.view.movie

import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
data class MovieVR(
    val id: Int?,
    val title: String,
    @DrawableRes var image: Int?,
    var overview: String,
    val voteAverage: Double,
    val tagline: String
) {

    companion object {
        private fun fromModel(movie: Movie): MovieVR {
            return MovieVR(
                movie.id,
                movie.title,
                movie.image,
                movie.overview,
                movie.voteAverage,
                movie.tagline
            )
        }

        fun toModel(movieVR: MovieVR): Movie {
            return Movie(
                movieVR.id,
                movieVR.title,
                movieVR.image,
                movieVR.overview,
                movieVR.voteAverage,
                movieVR.tagline
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