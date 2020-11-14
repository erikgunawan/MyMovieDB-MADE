package id.ergun.mymoviedb.ui.view.tvshow

import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
data class TvShowVR(
    val id: Int?,
    val title: String,
    @DrawableRes val image: Int?,
    val overview: String,
    val voteAverage: Double,
    val tagline: String
) {

    companion object {
        private fun fromModel(tvShow: TvShow): TvShowVR {
            return TvShowVR(
                tvShow.id,
                tvShow.title,
                tvShow.image,
                tvShow.overview,
                tvShow.voteAverage,
                tvShow.tagline
            )
        }

        fun toModel(tvShowVR: TvShowVR): TvShow {
            return TvShow(
                tvShowVR.id,
                tvShowVR.title,
                tvShowVR.image,
                tvShowVR.overview,
                tvShowVR.voteAverage,
                tvShowVR.tagline
            )
        }

        fun transform(list: ArrayList<TvShow>): ArrayList<TvShowVR> {
            val newList = arrayListOf<TvShowVR>()
            list.forEach {
                newList.add(fromModel(it))
            }

            return newList
        }
    }
}