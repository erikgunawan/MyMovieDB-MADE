package id.ergun.mymoviedb.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.view.FavoriteFragment
import id.ergun.mymoviedb.favorite.view.movie.MovieFragment
import id.ergun.mymoviedb.favorite.view.tvshow.TvShowFragment

/**
 * Created by alfacart on 17/01/21.
 */

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteFragmentComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteFragmentComponent
    }

}