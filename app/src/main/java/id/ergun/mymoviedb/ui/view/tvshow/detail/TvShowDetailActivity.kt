package id.ergun.mymoviedb.ui.view.tvshow.detail

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
import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.FavoriteModel
import id.ergun.mymoviedb.core.util.eventbus.FavoriteEvent
import id.ergun.mymoviedb.core.util.loadImage
import id.ergun.mymoviedb.core.util.share
import id.ergun.mymoviedb.databinding.TvShowDetailActivityBinding
import id.ergun.mymoviedb.ui.viewmodel.tvshow.TvShowViewModel
import org.greenrobot.eventbus.EventBus


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
            title = ""
        }

        loadIntents()
        initActions()

        updateData(viewModel.tvShow)

        getTvShowDetail()
        checkFavorite()
        loadIntents()


    }

    private fun updateData(tvShow: TvShow) {
        with(binding) {
            tvTitle.text = tvShow.title
            tvTagLine.text = tvShow.tagLine
            tvOverview.text = tvShow.overview
            viewRating.tvRating.text =  tvShow.voteAverage.toString()
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

    private fun checkFavorite() {
        viewModel.getFavoriteTvShowById().observe(this) { data ->
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
        viewModel.addToFavorite(viewModel.tvShow).observe(this) {
            EventBus.getDefault().post(
                FavoriteEvent(
                    Const.TV_SHOW_TYPE,
                    true
                )
            )
            viewModel.favorite.value = true
            viewModel.favoriteState.value = FavoriteModel.Type.ADD_TO_FAVORITE
        }
    }

    private fun removeFromFavorite() {
        if (viewModel.tvShow.id == null) return
        viewModel.removeFromFavorite(viewModel.tvShow.id ?: 0).observe(this) {
            EventBus.getDefault().post(
                FavoriteEvent(
                    Const.TV_SHOW_TYPE,
                    true
                )
            )
            viewModel.favorite.value = false
            viewModel.favoriteState.value = FavoriteModel.Type.REMOVE_FROM_FAVORITE
        }
    }

    private fun loadIntents() {
        if (intent.hasExtra(EXTRA_TV_SHOW)) {
            viewModel.setSelectedTvShow(intent.getParcelableExtra(EXTRA_TV_SHOW) ?: return)
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