package id.ergun.mymoviedb.data.repository.movie

import android.database.Observable
import androidx.lifecycle.LiveData
import id.ergun.mymoviedb.domain.model.Movie

/**
 * Created by alfacart on 21/10/20.
 */
interface MovieRepository {
    fun getMovies(): ArrayList<Movie>
}