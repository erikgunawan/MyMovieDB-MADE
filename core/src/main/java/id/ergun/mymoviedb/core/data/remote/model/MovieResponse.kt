package id.ergun.mymoviedb.core.data.remote.model

import com.google.gson.annotations.SerializedName
import id.ergun.mymoviedb.core.domain.model.Movie

/**
 * Created by alfacart on 20/11/20.
 */
data class MovieResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: MutableList<Result>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) {
  data class Result(
      @SerializedName("id")
      val id: Int?,
      @SerializedName("overview")
      val overview: String?,
      @SerializedName("poster_path")
      val posterPath: String?,
      @SerializedName("title")
      val title: String?,
      @SerializedName("vote_average")
      val voteAverage: Double?,
      @SerializedName("tagline")
      val tagLine: String?
  )

  companion object {
    fun mapToDomainModelList(item: MovieResponse): ArrayList<Movie> {
      val list = arrayListOf<Movie>()
      item.results?.forEach {
        list.add(mapToDomainModel(it))
      }
      return list
    }

    fun mapToDomainModel(item: Result): Movie {
      return Movie(
          id = item.id,
          title = item.title ?: "",
          posterPath = item.posterPath ?: "",
          overview = item.overview ?: "",
          voteAverage = item.voteAverage ?: 0.0,
          tagLine = item.tagLine ?: ""
      )
    }
  }
}