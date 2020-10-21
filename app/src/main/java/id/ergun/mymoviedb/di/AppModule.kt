package id.ergun.mymoviedb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.TvShowDB
import javax.inject.Singleton

/**
 * Created by alfacart on 21/10/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getMovieDB(@ApplicationContext appContext: Context): MovieDB = MovieDB(appContext)

    @Provides
    fun getTvShowDB(@ApplicationContext appContext: Context): TvShowDB = TvShowDB(appContext)
}