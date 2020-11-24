package id.ergun.mymoviedb.data.remote.model

import com.google.gson.annotations.SerializedName
import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 20/11/20.
 */
data class TvShowResponse(
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
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("first_air_date")
        val firstAirDate: String?,
        @SerializedName("genre_ids")
        val genreIds: MutableList<Int>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("origin_country")
        val originCountry: MutableList<String>?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?,
        @SerializedName("tagline")
        val tagLine: String?
    )

    companion object {
        fun mapToDomainModelList(item: TvShowResponse): ArrayList<TvShow> {
            val list = arrayListOf<TvShow>()
            item.results?.forEach {
                list.add(mapToDomainModel(it))
            }
            return list
        }
        fun mapToDomainModel(item: Result): TvShow {
            return TvShow(
                id = item.id,
                title = item.name ?: "",
                posterPath = item.posterPath ?: "",
                overview = item.overview ?: "",
                voteAverage = item.voteAverage ?: 0.0,
                tagLine = item.tagLine ?: ""
            )
        }
    }
}