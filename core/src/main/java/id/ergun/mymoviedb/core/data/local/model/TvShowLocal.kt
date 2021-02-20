package id.ergun.mymoviedb.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ergun.mymoviedb.core.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
@Entity(tableName = "TvShow")
data class TvShowLocal(
    @PrimaryKey val id: Int,
    val name: String = "",
    val overview: String = "",
    val voteAverage: Double = 0.0,
    val tagLine: String = "",
    val posterPath: String = ""
) {

  companion object {
    fun mapToDomainModelList(items: MutableList<TvShowLocal>): ArrayList<Movie> {
      val list = arrayListOf<Movie>()
      items.forEach {
        list.add(mapToDomainModel(it))
      }
      return list
    }

    fun mapToDomainModel(item: TvShowLocal): Movie {
      return Movie(
          id = item.id,
          title = item.name,
          posterPath = item.posterPath,
          overview = item.overview,
          voteAverage = item.voteAverage,
          tagLine = item.tagLine
      )
    }

    fun mapFromDomainModel(item: Movie): TvShowLocal {
      return TvShowLocal(
          id = item.id ?: 0,
          name = item.title,
          posterPath = item.posterPath,
          overview = item.overview,
          voteAverage = item.voteAverage,
          tagLine = item.tagLine
      )
    }
  }
}