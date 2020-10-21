package id.ergun.mymoviedb.domain.usecase.movie

import androidx.lifecycle.LiveData
import id.ergun.mymoviedb.domain.model.Movie
import kotlin.collections.ArrayList

/**
 * Created by alfacart on 21/10/20.
 */
interface MovieUseCase {
    fun getMovies(): ArrayList<Movie>
}