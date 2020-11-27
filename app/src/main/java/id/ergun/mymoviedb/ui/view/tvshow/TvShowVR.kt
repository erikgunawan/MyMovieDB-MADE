package id.ergun.mymoviedb.ui.view.tvshow

import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
data class TvShowVR(
    val id: Int?,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val tagLine: String,
    val posterPath: String
) {

    companion object {
        private fun fromModel(tvShow: TvShow): TvShowVR {
            return TvShowVR(
                tvShow.id,
                tvShow.title,
                tvShow.overview,
                tvShow.voteAverage,
                tvShow.tagLine,
                tvShow.posterPath
            )
        }

        fun toModel(tvShowVR: TvShowVR): TvShow {
            return TvShow(
                tvShowVR.id,
                tvShowVR.title,
                tvShowVR.overview,
                tvShowVR.voteAverage,
                tvShowVR.tagLine,
                tvShowVR.posterPath
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