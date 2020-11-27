package id.ergun.mymoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by alfacart on 21/10/20.
 */
@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey val id: Int,
    val overview: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val tagLine: String = "",
    val posterPath: String = ""
)