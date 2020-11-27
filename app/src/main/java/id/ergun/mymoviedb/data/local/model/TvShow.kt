package id.ergun.mymoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by alfacart on 21/10/20.
 */
@Entity(tableName = "TvShow")
data class TvShow(
    @PrimaryKey val id: Int,
    val name: String = "",
    val overview: String = "",
    val voteAverage: Double = 0.0,
    val tagLine: String = "",
    val posterPath: String = ""
)