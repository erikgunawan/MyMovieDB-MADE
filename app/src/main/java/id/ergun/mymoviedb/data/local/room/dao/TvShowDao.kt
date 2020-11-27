package id.ergun.mymoviedb.data.local.room.dao

import android.database.Cursor
import androidx.room.*
import id.ergun.mymoviedb.data.local.model.TvShow

/**
 * Created by alfacart on 26/11/20.
 */

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShow): Long

    @Update
    fun updateTvShow(tvShow: TvShow)

    @Delete
    fun deleteTvShow(tvShow: TvShow)

    @Query("SELECT * FROM TvShow")
    fun getTvShows(): MutableList<TvShow>

    @Query("SELECT * FROM TvShow WHERE id==:id")
    fun getTvShow(id: String): TvShow

    @Query("SELECT * FROM TvShow")
    fun getCursorTvShows(): Cursor

    @Query("SELECT * FROM TvShow WHERE id==:id")
    fun getCursorTvShow(id: String): Cursor

    @Query("DELETE FROM TvShow WHERE id==:id")
    fun deleteById(id: Long): Int
}