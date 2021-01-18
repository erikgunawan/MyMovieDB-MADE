package id.ergun.mymoviedb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by alfacart on 21/10/20.
 */
@Parcelize
class TvShow(
    var id: Int? = null,
    var title: String = "",
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var tagLine: String = "",
    var posterPath: String = ""
): Parcelable