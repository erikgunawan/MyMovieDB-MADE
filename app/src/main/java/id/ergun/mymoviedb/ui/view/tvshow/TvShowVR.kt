package id.ergun.mymoviedb.ui.view.tvshow

import androidx.annotation.DrawableRes
import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
data class TvShowVR(
    var id: Int? = null,
    var title: String = "",
    @DrawableRes var image: Int? = null,
    var overview: String = "") {

    companion object {
        fun fromModel(tvShow: TvShow): TvShowVR {
            return TvShowVR(
                tvShow.id,
                tvShow.title,
                tvShow.image,
                tvShow.overview
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