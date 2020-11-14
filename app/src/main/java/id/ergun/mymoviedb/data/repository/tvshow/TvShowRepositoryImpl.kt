package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.model.TvShow
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowRepositoryImpl @Inject constructor(private val localData: TvShowDB) : TvShowRepository {
    override fun getTvShows(): ArrayList<TvShow> {
        val list = arrayListOf<TvShow>()
        localData.getTvShows().forEach {
            list.add(
                TvShow(
                    it.id,
                    it.name,
                    it.image,
                    it.overview,
                    it.voteAverage,
                    it.tagline
                )
            )
        }
        return list
    }
}