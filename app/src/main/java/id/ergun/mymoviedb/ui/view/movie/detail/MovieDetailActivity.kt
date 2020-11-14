package id.ergun.mymoviedb.ui.view.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.databinding.MovieDetailActivityBinding
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.ui.viewmodel.movie.MovieViewModel
import id.ergun.mymoviedb.util.loadImage
import id.ergun.mymoviedb.util.share

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: MovieDetailActivityBinding

    private val viewModel by viewModels<MovieViewModel>()

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

        setSupportActionBar(binding.toolbarView.toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        loadIntents()

        with(binding) {
            tvTitle.text = viewModel.movie.title
            tvTagline.text = viewModel.movie.tagline
            tvOverview.text = viewModel.movie.overview
            ivPoster.loadImage(viewModel.movie.image)
        }
    }

    private fun loadIntents() {
        if (intent.hasExtra(EXTRA_MOVIE)) {
            viewModel.setSelectedMovie(intent.getParcelableExtra(EXTRA_MOVIE)!!)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_share -> {
                share(this, "movie", viewModel.movie.id.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }
}