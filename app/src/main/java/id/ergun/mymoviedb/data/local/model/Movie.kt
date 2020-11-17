package id.ergun.mymoviedb.data.local.model

import androidx.annotation.DrawableRes

/**
 * Created by alfacart on 21/10/20.
 */
data class Movie(
    val id: Int? = null,
    val overview: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val tagLine: String = "",
    @DrawableRes val image: Int
)