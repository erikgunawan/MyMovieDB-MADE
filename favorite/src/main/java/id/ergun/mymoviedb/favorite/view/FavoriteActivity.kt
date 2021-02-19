package id.ergun.mymoviedb.favorite.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.EntryPointAccessors
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.R
import id.ergun.mymoviedb.favorite.databinding.FavoriteActivityBinding
import id.ergun.mymoviedb.favorite.di.DaggerFavoriteActivityComponent

/**
 * Created by alfacart on 17/01/21.
 */
class FavoriteActivity : AppCompatActivity() {

  private lateinit var binding: FavoriteActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    DaggerFavoriteActivityComponent.builder()
        .context(this)
        .appDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                FavoriteModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
    super.onCreate(savedInstanceState)
    binding = FavoriteActivityBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    setSupportActionBar(binding.xx.toolbar)
    supportActionBar?.run {
      setDisplayShowHomeEnabled(true)
      setDisplayHomeAsUpEnabled(true)
      title = ""
    }

    loadFragment(FavoriteFragment.newInstance())
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

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }
}