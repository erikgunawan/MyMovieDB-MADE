package id.ergun.mymoviedb.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.core.util.autoCleared
import id.ergun.mymoviedb.databinding.HomeFragmentBinding

/**
 * Created by alfacart on 26/11/20.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
  private var binding: HomeFragmentBinding by autoCleared()

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = HomeFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  private fun initView() {
    val sectionsPagerAdapter = HomePagerAdapter(requireContext(), childFragmentManager)
    binding.viewPager.adapter = sectionsPagerAdapter
    binding.tabs.setupWithViewPager(binding.viewPager)
  }
}