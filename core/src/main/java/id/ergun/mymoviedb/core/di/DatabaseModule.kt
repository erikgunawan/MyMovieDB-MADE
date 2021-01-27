package id.ergun.mymoviedb.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ergun.mymoviedb.core.data.local.room.AppDatabase
import id.ergun.mymoviedb.core.data.local.room.dao.MovieDao
import id.ergun.mymoviedb.core.data.local.room.dao.TvShowDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

/**
 * Created by alfacart on 30/11/20.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mym0v13db".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyMovieDB.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun getMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    @Singleton
    fun getTvShowDao(appDatabase: AppDatabase): TvShowDao = appDatabase.tvShowDao()
}