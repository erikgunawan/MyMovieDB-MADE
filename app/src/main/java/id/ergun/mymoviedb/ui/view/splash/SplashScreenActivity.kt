package id.ergun.mymoviedb.ui.view.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ergun.mymoviedb.databinding.HomeActivityBinding
import id.ergun.mymoviedb.databinding.SplashScreenActivityBinding
import id.ergun.mymoviedb.ui.view.home.HomePagerAdapter

/**
 * Created by alfacart on 21/10/20.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: SplashScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}