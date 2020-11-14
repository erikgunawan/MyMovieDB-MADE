package id.ergun.mymoviedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.TvShowDB
import javax.inject.Singleton

/**
 * Created by alfacart on 21/10/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getMovieDB(): MovieDB = MovieDB()

    @Provides
    @Singleton
    fun getTvShowDB(): TvShowDB = TvShowDB()
}