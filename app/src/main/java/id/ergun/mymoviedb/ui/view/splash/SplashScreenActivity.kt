package id.ergun.mymoviedb.ui.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.ergun.mymoviedb.databinding.SplashScreenActivityBinding
import id.ergun.mymoviedb.ui.view.home.HomeActivity

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

    Handler(Looper.getMainLooper()).postDelayed({
      gotoHomeActivity()
    }, 1000)
  }

  private fun gotoHomeActivity() {
    startActivity(HomeActivity.newIntent(this))
    finish()
  }

}