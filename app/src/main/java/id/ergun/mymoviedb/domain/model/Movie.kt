package id.ergun.mymoviedb.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.data.local.MovieDB
import kotlinx.android.parcel.Parcelize

/**
 * Created by alfacart on 21/10/20.
 */
@Parcelize
class Movie(
    var id: Int? = null,
    var title: String = "",
    @DrawableRes var image: Int? = null,
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var tagLine: String = ""
): Parcelable {

    companion object {
        fun transform(movieDB: MovieDB): ArrayList<Movie> {
            val list = arrayListOf<Movie>()
            movieDB.getMovies().forEach {
                list.add(
                    Movie(
                        it.id,
                        it.title,
                        it.image,
                        it.overview,
                        it.voteAverage,
                        it.tagLine
                    )
                )
            }
            return list
        }
    }
}