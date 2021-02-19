package id.ergun.mymoviedb.ui.view.movie.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.databinding.SearchActivityBinding

/**
 * Created by root on 19/02/21.
 */
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: SearchActivityBinding

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
        fun newIntent(
            context: Context
        ): Intent {
            val intent = Intent(context, SearchActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}