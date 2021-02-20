package id.ergun.mymoviedb.ui.view.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.FavoriteModel
import id.ergun.mymoviedb.core.util.eventbus.FavoriteEvent
import id.ergun.mymoviedb.core.util.loadImage
import id.ergun.mymoviedb.core.util.share
import id.ergun.mymoviedb.databinding.MovieDetailActivityBinding
import id.ergun.mymoviedb.ui.viewmodel.movie.MovieDetailViewModel
import org.greenrobot.eventbus.EventBus

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

  private lateinit var binding: MovieDetailActivityBinding

  private val viewModel by viewModels<MovieDetailViewModel>()

  companion object {
    const val EXTRA_MOVIE = "EXTRA_MOVIE"
    const val EXTRA_PAGE_TYPE = "EXTRA_PAGE_TYPE"
    fun newIntent(
        context: Context, pageType: Int, movie: Movie
    ): Intent {
      val intent = Intent(context, MovieDetailActivity::class.java)
      intent.putExtra(EXTRA_MOVIE, movie)
      intent.putExtra(EXTRA_PAGE_TYPE, pageType)
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
      title = ""
    }

    loadIntents()
    initActions()

    updateData(viewModel.movie)

    getMovieDetail()
    checkFavorite()
  }

  private fun updateData(movie: Movie) {
    with(binding) {
      tvTitle.text = movie.title
      tvTagLine.text = movie.tagLine
      tvOverview.text = movie.overview
      viewRating.tvRating.text = movie.voteAverage.toString()
      ivPoster.loadImage(BuildConfig.IMAGE_URL + movie.posterPath)
    }
  }

  private fun getMovieDetail() {
    viewModel.movie.id?.let {
      viewModel.getMovieDetail(it).observe(this) { data ->
        val movie = data.data ?: return@observe
        updateData(movie)
      }
    }
  }

  private fun checkFavorite() {
    viewModel.getFavoriteMovieById().observe(this) { data ->
      viewModel.favorite.value = data.data != null
    }

    viewModel.favorite.observe(this) {
      if (it == null) return@observe
      binding.fabFavorite.setImageDrawable(
          ContextCompat.getDrawable(
              this,
              if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_unchecked
          )
      )
    }

    viewModel.favoriteState.observe(this) {
      val message = if (it == FavoriteModel.Type.ADD_TO_FAVORITE)
        getString(R.string.message_add_to_favorite)
      else getString(R.string.message_remove_from_favorite)

      val snackBar = Snackbar.make(binding.container, message, Snackbar.LENGTH_LONG)
      snackBar.show()
    }
  }

  private fun addToFavorite() {
    viewModel.addToFavorite(viewModel.movie).observe(this) {
      EventBus.getDefault().post(
          FavoriteEvent(
              Const.MOVIE_TYPE,
              true
          )
      )
      viewModel.favorite.value = true
      viewModel.favoriteState.value = FavoriteModel.Type.ADD_TO_FAVORITE
    }
  }

  private fun removeFromFavorite() {
    if (viewModel.movie.id == null) return
    viewModel.removeFromFavorite(viewModel.movie.id ?: 0).observe(this) {
      EventBus.getDefault().post(
          FavoriteEvent(
              Const.MOVIE_TYPE,
              true
          )
      )
      viewModel.favorite.value = false
      viewModel.favoriteState.value = FavoriteModel.Type.REMOVE_FROM_FAVORITE
    }
  }

  private fun loadIntents() {
    viewModel.pageType = intent?.getIntExtra(EXTRA_PAGE_TYPE, Const.MOVIE_TYPE) ?: Const.MOVIE_TYPE

    if (intent.hasExtra(EXTRA_MOVIE)) {
      viewModel.setSelectedMovie(intent.getParcelableExtra(EXTRA_MOVIE) ?: return)
    }
  }

  private fun initActions() {
    binding.fabFavorite.setOnClickListener {
      if (viewModel.favorite.value == true) removeFromFavorite()
      else addToFavorite()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
      R.id.action_share -> share(this, "movie", viewModel.movie.id.toString())
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_share, menu)
    return super.onCreateOptionsMenu(menu)
  }
}