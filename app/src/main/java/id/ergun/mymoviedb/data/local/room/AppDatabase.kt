package id.ergun.mymoviedb.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ergun.mymoviedb.data.local.model.Movie
import id.ergun.mymoviedb.data.local.model.TvShow
import id.ergun.mymoviedb.data.local.room.dao.MovieDao
import id.ergun.mymoviedb.data.local.room.dao.TvShowDao

/**
 * Created by alfacart on 26/11/20.
 */
@Database(entities = [Movie::class, TvShow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun instance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyMovieDB.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroy() {
            INSTANCE = null
        }
    }
}