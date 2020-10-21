package id.ergun.mymoviedb.data.local

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.TvShow
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */

class TvShowDB @Inject constructor(private val context: Context)  {

    fun getTvShows(): MutableList<TvShow> {
        return getStaticTvShows()
    }

    private fun getStaticTvShows(): MutableList<TvShow> {
        val list: MutableList<TvShow> = mutableListOf()
        list.add(
            TvShow(
                id = 1412,
                name = context.getString(R.string.tv_show_arrow_title),
                image = R.drawable.tv_poster_arrow,
                overview = context.getString(R.string.tv_show_arrow_overview)
            )
        )
        list.add(
            TvShow(
                id = 12609,
                name = context.getString(R.string.tv_show_dragon_ball_title),
                image = R.drawable.tv_poster_dragon_ball,
                overview = context.getString(R.string.tv_show_dragon_ball_overview)
            )
        )
        list.add(
            TvShow(
                id = 46261,
                name = context.getString(R.string.tv_show_fairy_tail_title),
                image = R.drawable.tv_poster_fairytail,
                overview = context.getString(R.string.tv_show_fairy_tail_overview)
            )
        )
        list.add(
            TvShow(
                id = 1434,
                name = context.getString(R.string.tv_show_family_guy_title),
                image = R.drawable.tv_poster_family_guy,
                overview = context.getString(R.string.tv_show_family_guy_overview)
            )
        )
        list.add(
            TvShow(
                id = 60735,
                name = context.getString(R.string.tv_show_flash_title),
                image = R.drawable.tv_poster_flash,
                overview = context.getString(R.string.tv_show_flash_overview)
            )
        )
        list.add(
            TvShow(
                id = 60708,
                name = context.getString(R.string.tv_show_gotham_title),
                image = R.drawable.tv_poster_gotham,
                overview = context.getString(R.string.tv_show_gotham_overview)
            )
        )
        list.add(
            TvShow(
                id = 1416,
                name = context.getString(R.string.tv_show_grey_anatomy_title),
                image = R.drawable.tv_poster_grey_anatomy,
                overview = context.getString(R.string.tv_show_grey_anatomy_overview)
            )
        )
        list.add(
            TvShow(
                id = 54155,
                name = context.getString(R.string.tv_show_hanna_title),
                image = R.drawable.tv_poster_hanna,
                overview = context.getString(R.string.tv_show_hanna_overview)
            )
        )
        list.add(
            TvShow(
                id = 1412,
                name = context.getString(R.string.tv_show_iron_fist_title),
                image = R.drawable.tv_poster_iron_fist,
                overview = context.getString(R.string.tv_show_iron_fist_overview)
            )
        )
        list.add(
            TvShow(
                id = 31910,
                name = context.getString(R.string.tv_show_naruto_shippuden_title),
                image = R.drawable.tv_poster_naruto_shipudden,
                overview = context.getString(R.string.tv_show_naruto_shippuden_overview)
            )
        )
        return list
    }
}