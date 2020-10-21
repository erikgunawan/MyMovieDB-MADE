package id.ergun.mymoviedb.ui.view.movie

import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
data class MovieVR(
    var id: Int? = null,
    var title: String = "",
    @DrawableRes var image: Int? = null,
    var overview: String = "") {

    companion object {
        fun fromModel(movie: Movie): MovieVR {
            return MovieVR(
                movie.id,
                movie.title,
                movie.image,
                movie.overview
            )
        }

        fun toModel(movieVR: MovieVR): Movie {
            return Movie(
                movieVR.id,
                movieVR.title,
                movieVR.image,
                movieVR.overview
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