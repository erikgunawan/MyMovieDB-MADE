package id.ergun.mymoviedb.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.data.local.TvShowDB
import kotlinx.android.parcel.Parcelize

/**
 * Created by alfacart on 21/10/20.
 */
@Parcelize
class TvShow(
    var id: Int? = null,
    var title: String = "",
    @DrawableRes var image: Int? = null,
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var tagline: String = ""
): Parcelable {

    companion object {
        fun transform(tvShowDB: TvShowDB): ArrayList<TvShow> {
            val list = arrayListOf<TvShow>()
            tvShowDB.getTvShows().forEach {
                list.add(
                    TvShow(
                        it.id,
                        it.name,
                        it.image,
                        it.overview,
                        it.voteAverage,
                        it.tagline
                    )
                )
            }
            return list
        }
    }
}