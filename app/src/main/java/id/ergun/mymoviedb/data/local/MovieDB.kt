package id.ergun.mymoviedb.data.local

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.Movie
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieDB @Inject constructor(private val context: Context) {

    fun getDummyMovies(): MutableList<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.add(
            Movie(
                id = 332562,
                title = context.getString(R.string.movie_a_star_is_born_title),
                image = R.drawable.movie_poster_a_start_is_born,
                overview = context.getString(R.string.movie_a_star_is_born_overview)
            )
        )
        list.add(
            Movie(
                id = 297802,
                title = context.getString(R.string.movie_aquaman_title),
                image = R.drawable.movie_poster_aquaman,
                overview = context.getString(R.string.movie_aquaman_overview)
            )
        )
        list.add(
            Movie(
                id = 299536,
                title = context.getString(R.string.movie_avenger_infinity_war_title),
                image = R.drawable.movie_poster_avengerinfinity,
                overview = context.getString(R.string.movie_avenger_infinity_war_overview)
            )
        )
        list.add(
            Movie(
                id = 405774,
                title = context.getString(R.string.movie_bird_box_title),
                image = R.drawable.movie_poster_birdbox,
                overview = context.getString(R.string.movie_bird_box_overview)
            )
        )
        list.add(
            Movie(
                id = 424694,
                title = context.getString(R.string.movie_bohemian_rapsody_title),
                image = R.drawable.movie_poster_bohemian,
                overview = context.getString(R.string.movie_bohemian_rapsody_overview)
            )
        )
        list.add(
            Movie(
                id = 424783,
                title = context.getString(R.string.movie_bumblebee_title),
                image = R.drawable.movie_poster_bumblebee,
                overview = context.getString(R.string.movie_bumblebee_overview)
            )
        )
        list.add(
            Movie(
                id = 480530,
                title = context.getString(R.string.movie_creed_ii_title),
                image = R.drawable.movie_poster_creed,
                overview = context.getString(R.string.movie_creed_ii_overview)
            )
        )
        list.add(
            Movie(
                id = 293660,
                title = context.getString(R.string.movie_deadpool_title),
                image = R.drawable.movie_poster_deadpool,
                overview = context.getString(R.string.movie_deadpool_overview)
            )
        )
        list.add(
            Movie(
                id = 166428,
                title = context.getString(R.string.movie_how_to_train_your_dragon_title),
                image = R.drawable.tv_poster_dragon_ball,
                overview = context.getString(R.string.movie_how_to_train_your_dragon_overview)
            )
        )
        list.add(
            Movie(
                id = 503314,
                title = context.getString(R.string.movie_dragon_ball_super_broly_title),
                image = R.drawable.tv_poster_dragon_ball,
                overview = context.getString(R.string.movie_dragon_ball_super_broly_overview)
            )
        )
        list.add(
            Movie(
                id = 450465,
                title = context.getString(R.string.movie_glass_title),
                image = R.drawable.movie_poster_glass,
                overview = context.getString(R.string.movie_glass_overview)
            )
        )
        list.add(
            Movie(
                id = 399402,
                title = context.getString(R.string.movie_hunter_killer_title),
                image = R.drawable.movie_poster_hunterkiller,
                overview = context.getString(R.string.movie_hunter_killer_overview)
            )
        )
        return list
    }
}