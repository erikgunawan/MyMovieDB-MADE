package id.ergun.mymoviedb.data.local.room.dao

import android.database.Cursor
import androidx.room.*
import id.ergun.mymoviedb.data.local.model.Movie

/**
 * Created by alfacart on 26/11/20.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getMovies(): MutableList<Movie>

    @Query("SELECT * FROM Movie WHERE id==:id")
    fun getMovie(id: String): Movie

    @Query("SELECT * FROM Movie")
    fun getCursorMovies(): Cursor

    @Query("SELECT * FROM Movie WHERE id==:id")
    fun getCursorMovie(id: String): Cursor

    @Query("DELETE FROM Movie WHERE id==:id")
    fun deleteById(id: Long): Int


}