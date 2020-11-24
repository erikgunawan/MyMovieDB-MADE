package id.ergun.mymoviedb.data

import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.remote.model.TvShowResponse
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 24/11/20.
 */
object FakeTvShowDB {

    fun getTvShows(localData: TvShowDB): Resource<ArrayList<TvShow>> {
        return Resource(
            data = TvShow.transform(localData),
            status = Resource.Status.SUCCESS,
            message = null
        )
    }

    fun getTvShowDetail(tvShow: TvShow): Resource<TvShow> {
        return Resource(
            data = tvShow,
            status = Resource.Status.SUCCESS,
            message = null
        )
    }

    fun getTvShowsRemote(localData: TvShowDB): TvShowResponse {
        val data = mutableListOf<TvShowResponse.Result>()

        localData.getTvShows().forEach {
            data.add(
                TvShowResponse.Result(
                    backdropPath = null,
                    firstAirDate = null,
                    genreIds = null,
                    id = it.id,
                    originCountry = null,
                    originalLanguage = null,
                    originalName = null,
                    overview = it.overview,
                    popularity = null,
                    posterPath = it.posterPath,
                    name = it.name,
                    voteAverage = it.voteAverage,
                    voteCount = null,
                    tagLine = it.tagLine
                ))
        }
        return TvShowResponse(
            page = 1,
            totalPages = 10,
            totalResults = 10,
            results = data
        )
    }

    fun getTvShowDetailRemote(tvShow: TvShow): TvShowResponse.Result {
        return TvShowResponse.Result(
            backdropPath = null,
            firstAirDate = null,
            genreIds = null,
            id = tvShow.id,
            originCountry = null,
            originalLanguage = null,
            originalName = null,
            overview = tvShow.overview,
            popularity = null,
            posterPath = tvShow.posterPath,
            name = tvShow.title,
            voteAverage = tvShow.voteAverage,
            voteCount = null,
            tagLine = tvShow.tagLine
        )
    }
}