package id.ergun.mymoviedb.ui.view.movie.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.util.gone
import id.ergun.mymoviedb.core.util.visible
import id.ergun.mymoviedb.core.view.movie.MovieAdapter
import id.ergun.mymoviedb.databinding.SearchActivityBinding
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.ui.view.tvshow.detail.TvShowDetailActivity
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
    getMovies()
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
    movieAdapter.itemClickListener = { movie ->
      if (movieViewModel.pageType == Const.MOVIE_TYPE)
        startActivity(MovieDetailActivity.newIntent(this, movieViewModel.pageType, movie))
      else
        startActivity(TvShowDetailActivity.newIntent(this, movie))
    }

    binding.viewWarning.btnWarning.setOnClickListener {
      movieViewModel.refresh()
    }
  }

  private fun getMovies() {
    movieViewModel.search("naruto").observe(this) {
      movieAdapter.submitList(it)
      movieAdapter.notifyDataSetChanged()
    }

    movieViewModel.state.observe(this) {
      when (it.status) {
        Resource.Status.LOADING -> showLoading()
        Resource.Status.SUCCESS -> showData()
        Resource.Status.EMPTY_DATA -> showEmptyData(it.message.toString())
        Resource.Status.ERROR -> showWarning(it.message.toString())
      }
    }
  }

  private fun showLoading() {
    binding.wrapperContent.gone()
    binding.wrapperWarning.gone()
    binding.progressBar.visible()
  }

  private fun showData() {
    binding.wrapperContent.visible()
    binding.wrapperWarning.gone()
    binding.progressBar.gone()
  }

  private fun showEmptyData(message: String) {
    binding.wrapperContent.gone()
    binding.viewWarning.btnWarning.gone()
    binding.wrapperWarning.visible()
    binding.progressBar.gone()

    binding.viewWarning.tvWarning.text = message
  }

  private fun showWarning(message: String) {
    binding.wrapperContent.gone()
    binding.viewWarning.btnWarning.visible()
    binding.wrapperWarning.visible()
    binding.progressBar.gone()

    binding.viewWarning.tvWarning.text = message
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

}