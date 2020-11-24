package id.ergun.mymoviedb.ui.view.tvshow.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.databinding.TvShowDetailActivityBinding
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.ui.viewmodel.tvshow.TvShowViewModel
import id.ergun.mymoviedb.util.loadImage
import id.ergun.mymoviedb.util.share

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: TvShowDetailActivityBinding

    private val viewModel by viewModels<TvShowViewModel>()

    companion object {
        const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
        fun newIntent(
            context: Context, tvShow: TvShow
        ): Intent {
            val intent = Intent(context, TvShowDetailActivity::class.java)
            intent.putExtra(EXTRA_TV_SHOW, tvShow)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TvShowDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarView.toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        loadIntents()

        updateData(viewModel.tvShow)

        getTvShowDetail()
    }

    private fun updateData(tvShow: TvShow) {
        with(binding) {
            tvTitle.text = tvShow.title
            tvTagLine.text = tvShow.tagLine
            tvOverview.text = tvShow.overview
            tvRating.text = tvShow.voteAverage.toString()
            ivPoster.loadImage(BuildConfig.IMAGE_URL + tvShow.posterPath)
        }
    }

    private fun getTvShowDetail() {
        viewModel.tvShow.id?.let {
            viewModel.getTvShowDetail(it).observe(this) { data ->
                val tvShow = data.data ?: return@observe
                updateData(tvShow)
            }
        }
    }

    private fun loadIntents() {
        if (intent.hasExtra(EXTRA_TV_SHOW)) {
            viewModel.setSelectedTvShow(intent.getParcelableExtra(EXTRA_TV_SHOW)!!)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_share -> {
                share(this, "tv", viewModel.tvShow.id.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }
}