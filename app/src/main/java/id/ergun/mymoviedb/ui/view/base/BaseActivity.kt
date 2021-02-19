package id.ergun.mymoviedb.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by alfacart on 15/12/20.
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
  protected open var binding: V? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getInflatedLayout(layoutInflater))
    setup()
  }

  override fun onDestroy() {
    super.onDestroy()
    this.binding = null
  }

  //internal functions
  private fun getInflatedLayout(inflater: LayoutInflater): View {
    this.binding = setBinding(inflater)


    return binding?.root
        ?: error("Please add your inflated binding class instance")
  }

  //abstract functions
  abstract fun setBinding(layoutInflater: LayoutInflater): V

  abstract fun setup()
}