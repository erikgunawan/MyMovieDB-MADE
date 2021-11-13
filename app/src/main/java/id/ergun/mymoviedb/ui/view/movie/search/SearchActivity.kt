package id.ergun.mymoviedb.ui.view.movie.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.DebouncingQueryTextListener
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.util.gone
import id.ergun.mymoviedb.core.util.loadImage
import id.ergun.mymoviedb.core.util.visible
import id.ergun.mymoviedb.core.view.movie.MovieAdapter
import id.ergun.mymoviedb.databinding.SearchActivityBinding
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.ui.viewmodel.search.SearchViewModel

/**
 * Created by root on 19/02/21.
 */
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_PAGE_TYPE: String = "EXTRA_PAGE_TYPE"
    fun newIntent(
        context: Context,
        pageType: Int
    ): Intent {
      val intent = Intent(context, SearchActivity::class.java)
      intent.putExtra(EXTRA_PAGE_TYPE, pageType)
      return intent
    }
  }

  private lateinit var binding: SearchActivityBinding

  private val movieViewModel by viewModels<SearchViewModel>()

  private lateinit var movieAdapter: MovieAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = SearchActivityBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    setSupportActionBar(binding.toolbarView.toolbar)
    supportActionBar?.run {
      setDisplayShowHomeEnabled(true)
      setDisplayHomeAsUpEnabled(true)
      title = ""
    }
    loadExtras()
    adjustView()
    initView()
    initAction()
    getSearchState()
    showInitSearch()
  }

  private fun loadExtras() {
    movieViewModel.pageType = intent?.extras?.getInt(EXTRA_PAGE_TYPE) ?: Const.MOVIE_TYPE
  }

  private fun adjustView() {
    binding.toolbarView.searchView.queryHint = if (movieViewModel.pageType == Const.MOVIE_TYPE)
      "Cari Movie" else "Cari TV Show"
  }

  private fun initView() {
    movieAdapter = MovieAdapter()

    with(binding.rvMovie) {
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      adapter = movieAdapter
    }
  }

  private fun initAction() {
    movieAdapter.itemClickListener = { view, movie ->

      val intent = MovieDetailActivity.newIntent(this, movieViewModel.pageType, movie)

      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
          view, Const.POSTER_TRANSITION_NAME)

      startActivity(intent, options.toBundle())
    }

    binding.viewWarning.btnWarning.setOnClickListener {
      movieViewModel.refresh()
    }

    binding.toolbarView.searchView.setOnQueryTextListener(
        DebouncingQueryTextListener(
            this.lifecycle
        ) { newText ->
          newText?.let { query ->
            if (query.isEmpty()) {
              resetSearch()
            } else {
              movieViewModel.search(query).observe(this) {
                movieAdapter.submitList(it)
                movieAdapter.notifyDataSetChanged()
              }
            }
          }
        }
    )
  }

  private fun getSearchState() {
    movieViewModel.getState().observe(this) {
      when (it.status) {
        Resource.Status.LOADING -> showLoading()
        Resource.Status.SUCCESS -> showData()
        Resource.Status.EMPTY_DATA -> showEmptyData(it.message.toString())
        Resource.Status.ERROR -> showWarning(it.message.toString())
      }
    }
  }

  private fun resetSearch() {
    showInitSearch()
    movieAdapter.submitList(null)
    movieAdapter.notifyDataSetChanged()
  }

  private fun showInitSearch() {
    val type = if (movieViewModel.pageType == Const.MOVIE_TYPE) "movie" else "tv show"
    binding.run {
      tvHintSearch.text = getString(R.string.search_hint_description, type)
      tvHintSearch.visible()
      rvMovie.gone()
      viewWarning.root.gone()
      progressBar.gone()
    }
  }

  private fun showLoading() {
    binding.run {
      tvHintSearch.gone()
      rvMovie.gone()
      viewWarning.root.gone()
      progressBar.visible()
    }
  }

  private fun showData() {
    binding.run {
      tvHintSearch.gone()
      rvMovie.visible()
      viewWarning.root.gone()
      progressBar.gone()
    }
  }

  private fun showEmptyData(message: String) {
    binding.run {
      tvHintSearch.gone()
      rvMovie.gone()
      viewWarning.btnWarning.gone()
      viewWarning.root.visible()
      progressBar.gone()

      viewWarning.ivWarning.loadImage(R.drawable.img_empty)
      viewWarning.tvWarning.text = message
    }
  }

  private fun showWarning(message: String) {
    binding.run {
      tvHintSearch.gone()
      rvMovie.gone()
      viewWarning.btnWarning.visible()
      viewWarning.root.visible()
      progressBar.gone()

      viewWarning.ivWarning.loadImage(R.drawable.img_error)
      viewWarning.tvWarning.text = message
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

}