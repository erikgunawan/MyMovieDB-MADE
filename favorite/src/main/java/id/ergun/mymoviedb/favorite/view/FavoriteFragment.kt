package id.ergun.mymoviedb.favorite.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.EntryPointAccessors
import id.ergun.mymoviedb.core.util.autoCleared
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.databinding.FavoriteFragmentBinding
import id.ergun.mymoviedb.favorite.di.DaggerFavoriteFragmentComponent

/**
 * Created by alfacart on 26/11/20.
 */
class FavoriteFragment : Fragment() {

  companion object {
    fun newInstance(): FavoriteFragment {
      return FavoriteFragment()
    }
  }

  private var binding: FavoriteFragmentBinding by autoCleared()

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    DaggerFavoriteFragmentComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext().applicationContext,
                FavoriteModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
    binding = FavoriteFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  private fun initView() {
    val sectionsPagerAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
    binding.viewPager.adapter = sectionsPagerAdapter
    binding.tabs.setupWithViewPager(binding.viewPager)
  }
}