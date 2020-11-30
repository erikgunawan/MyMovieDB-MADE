package id.ergun.mymoviedb.data.local.room.dao

import android.database.Cursor
import androidx.room.*
import id.ergun.mymoviedb.data.local.model.MovieLocal

/**
 * Created by alfacart on 26/11/20.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieLocal): Long

    @Update
    suspend fun updateMovie(movie: MovieLocal)

    @Delete
    suspend fun deleteMovie(movie: MovieLocal)

    @Query("SELECT * FROM Movie")
    suspend fun getMovies(): MutableList<MovieLocal>

    @Query("SELECT * FROM Movie WHERE id==:id")
    suspend fun getMovie(id: Int): MovieLocal?

    @Query("SELECT * FROM Movie")
    fun getCursorMovies(): Cursor

    @Query("SELECT * FROM Movie WHERE id==:id")
    fun getCursorMovie(id: Int): Cursor

    @Query("DELETE FROM Movie WHERE id==:id")
    suspend fun deleteById(id: Int): Int

}