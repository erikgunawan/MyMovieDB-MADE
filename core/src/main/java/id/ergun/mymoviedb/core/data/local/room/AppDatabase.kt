package id.ergun.mymoviedb.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ergun.mymoviedb.core.data.local.model.MovieLocal
import id.ergun.mymoviedb.core.data.local.model.TvShowLocal
import id.ergun.mymoviedb.core.data.local.room.dao.MovieDao
import id.ergun.mymoviedb.core.data.local.room.dao.TvShowDao

/**
 * Created by alfacart on 26/11/20.
 */
@Database(entities = [MovieLocal::class, TvShowLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvShowDao(): TvShowDao
}