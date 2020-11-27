package id.ergun.mymoviedb.ui.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.databinding.MainActivityBinding
import id.ergun.mymoviedb.ui.view.home.HomeFragment

/**
 * Created by alfacart on 26/11/20.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    companion object {
        fun newIntent(
            context: Context
        ): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarView.toolbar)
        supportActionBar?.run {
            title = getString(R.string.app_name)
            elevation = 0F
        }

        loadFragment(HomeFragment())
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

}