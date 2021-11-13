@file:Suppress("DEPRECATION")

package id.ergun.mymoviedb.favorite.view

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.favorite.view.movie.MovieFragment

/**
 * Created by alfacart on 21/10/20.
 */
class FavoritePagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  companion object {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
  }

  override fun getItem(position: Int): Fragment =
      when (position) {
        0 -> MovieFragment.newInstance(Const.MOVIE_TYPE, true)
        1 -> MovieFragment.newInstance(Const.TV_SHOW_TYPE, true)
        else -> Fragment()
      }

  override fun getPageTitle(position: Int): CharSequence =
      mContext.resources.getString(TAB_TITLES[position])

  override fun getCount(): Int = TAB_TITLES.size

}