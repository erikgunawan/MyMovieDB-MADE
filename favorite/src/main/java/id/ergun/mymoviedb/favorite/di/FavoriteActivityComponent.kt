package id.ergun.mymoviedb.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.view.FavoriteActivity

/**
 * Created by alfacart on 17/01/21.
 */
@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteActivityComponent {

    fun inject(activity: FavoriteActivity)


    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteActivityComponent
    }

}
