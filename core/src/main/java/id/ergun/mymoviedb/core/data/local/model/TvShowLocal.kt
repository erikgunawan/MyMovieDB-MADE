package id.ergun.mymoviedb.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ergun.mymoviedb.core.domain.model.TvShow

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
    fun mapToDomainModelList(items: MutableList<TvShowLocal>): ArrayList<TvShow> {
      val list = arrayListOf<TvShow>()
      items.forEach {
        list.add(mapToDomainModel(it))
      }
      return list
    }

    fun mapToDomainModel(item: TvShowLocal): TvShow {
      return TvShow(
          id = item.id,
          title = item.name,
          posterPath = item.posterPath,
          overview = item.overview,
          voteAverage = item.voteAverage,
          tagLine = item.tagLine
      )
    }

    fun mapFromDomainModel(item: TvShow): TvShowLocal {
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