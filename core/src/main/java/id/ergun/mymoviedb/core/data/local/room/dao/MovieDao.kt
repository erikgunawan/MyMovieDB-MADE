package id.ergun.mymoviedb.core.data.local.room.dao

import androidx.room.*
import id.ergun.mymoviedb.core.data.local.model.MovieLocal

/**
 * Created by alfacart on 26/11/20.
 */

@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovie(movie: MovieLocal): Long

  @Update
  suspend fun updateMovie(movie: MovieLocal)

  @Query("SELECT * FROM Movie")
  suspend fun getMovies(): MutableList<MovieLocal>

  @Query("SELECT * FROM Movie WHERE id==:id")
  suspend fun getMovie(id: Int): MovieLocal?

  @Query("DELETE FROM Movie WHERE id==:id")
  suspend fun deleteById(id: Int): Int
}