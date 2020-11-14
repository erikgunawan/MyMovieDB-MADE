package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowUseCase {
    fun getTvShows(): ArrayList<TvShow>
}