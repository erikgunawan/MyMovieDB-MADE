package id.ergun.mymoviedb.data.local.model

import androidx.annotation.DrawableRes

/**
 * Created by alfacart on 21/10/20.
 */
data class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: MutableList<Int>? = mutableListOf(),
    val id: Int? = null,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val tagline: String = "",
    @DrawableRes val image: Int
)