package id.ergun.mymoviedb.ui.view.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.databinding.HomeActivityBinding
import id.ergun.mymoviedb.ui.view.movie.MovieFragment
import id.ergun.mymoviedb.ui.view.movie.search.SearchActivity
import id.ergun.mymoviedb.ui.view.tvshow.TvShowFragment
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: HomeActivityBinding

    @Inject
    lateinit var model: HomeModel

    companion object {
        fun newIntent(
            context: Context
        ): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarView.toolbar)
        supportActionBar?.run {
            title = ""
        }

        loadFragment(MovieFragment())

        binding.bnvMain.setOnNavigationItemSelectedListener(this)
    }


    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_view, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        if (binding.bnvMain.selectedItemId == item.itemId) return false

        when (item.itemId) {
            R.id.action_movies -> {
                model.activePage = Const.MOVIE_TYPE
                fragment = MovieFragment.newInstance()
            }
            R.id.action_tv_shows -> {
                model.activePage = Const.TV_SHOW_TYPE
                fragment = TvShowFragment.newInstance()
            }
            R.id.action_favorites -> {
                val uri = Uri.parse("mymoviedb://favorites")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                fragment = null
            }
        }
        return loadFragment(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity(SearchActivity.newIntent(this, model.activePage))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}