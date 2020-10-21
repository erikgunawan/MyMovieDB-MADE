package id.ergun.mymoviedb.ui.view.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.databinding.HomeActivityBinding
import id.ergun.mymoviedb.databinding.MovieDetailActivityBinding
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.ui.view.home.HomePagerAdapter

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: MovieDetailActivityBinding

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        fun newIntent(
            context: Context, movie: Movie
        ): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}