package id.ergun.mymoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
@Entity(tableName = "Movie")
data class MovieLocal(
    @PrimaryKey val id: Int,
    val overview: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val tagLine: String = "",
    val posterPath: String = ""
) {
    companion object {
        fun mapToDomainModelList(items: MutableList<MovieLocal>): ArrayList<Movie> {
            val list = arrayListOf<Movie>()
            items.forEach {
                list.add(mapToDomainModel(it))
            }
            return list
        }
        fun mapToDomainModel(item: MovieLocal): Movie {
            return Movie(
                id = item.id,
                title = item.title,
                posterPath = item.posterPath,
                overview = item.overview,
                voteAverage = item.voteAverage,
                tagLine = item.tagLine
            )
        }

        fun mapFromDomainModel(item: Movie): MovieLocal {
            return MovieLocal(
                id = item.id ?: 0,
                title = item.title,
                posterPath = item.posterPath,
                overview = item.overview,
                voteAverage = item.voteAverage,
                tagLine = item.tagLine
            )
        }
    }
}