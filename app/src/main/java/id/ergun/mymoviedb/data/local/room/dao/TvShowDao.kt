package id.ergun.mymoviedb.data.local.room.dao

import android.database.Cursor
import androidx.room.*
import id.ergun.mymoviedb.data.local.model.TvShowLocal

/**
 * Created by alfacart on 26/11/20.
 */

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShowLocal): Long

    @Update
    suspend fun updateTvShow(tvShow: TvShowLocal)

    @Delete
    suspend fun deleteTvShow(tvShow: TvShowLocal)

    @Query("SELECT * FROM TvShow")
    suspend fun getTvShows(): MutableList<TvShowLocal>

    @Query("SELECT * FROM TvShow WHERE id==:id")
    suspend fun getTvShow(id: Int): TvShowLocal?

    @Query("SELECT * FROM TvShow")
    fun getCursorTvShows(): Cursor

    @Query("SELECT * FROM TvShow WHERE id==:id")
    fun getCursorTvShow(id: Int): Cursor

    @Query("DELETE FROM TvShow WHERE id==:id")
    suspend fun deleteById(id: Int): Int
}