package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowRepository {
    fun getTvShows(): ArrayList<TvShow>
}