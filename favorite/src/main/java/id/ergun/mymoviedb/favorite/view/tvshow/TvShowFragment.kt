package id.ergun.mymoviedb.favorite.view.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.util.eventbus.FavoriteEvent
import id.ergun.mymoviedb.core.util.gone
import id.ergun.mymoviedb.core.util.visible
import id.ergun.mymoviedb.core.view.tvshow.TvShowAdapter
import id.ergun.mymoviedb.databinding.TvShowFragmentBinding
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.di.DaggerFavoriteFragmentComponent
import id.ergun.mymoviedb.favorite.viewmodel.tvshow.TvShowViewModel
import id.ergun.mymoviedb.favorite.viewmodel.tvshow.TvShowViewModelFactory
import id.ergun.mymoviedb.ui.view.tvshow.detail.TvShowDetailActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowFragment : Fragment() {

    companion object {
        private const val ARGUMENT_FAVORITE = "ARGUMENT_FAVORITE"
        fun newInstance(
            favorite: Boolean = false
        ): TvShowFragment {
            val fragment = TvShowFragment()
            val argument = Bundle()
            argument.putBoolean(ARGUMENT_FAVORITE, favorite)
            fragment.arguments = argument
            return fragment
        }
    }

    private lateinit var binding: TvShowFragmentBinding

    @Inject
    lateinit var factory: TvShowViewModelFactory

    private val tvShowViewModel: TvShowViewModel by viewModels {
        factory
    }

    private lateinit var tvShowAdapter: TvShowAdapter

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
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEventBus()
        loadArgument()
        initView()
        initAction()
        getTvShows()
    }

    private fun initEventBus() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    private fun loadArgument() {
        if (arguments == null) return

        tvShowViewModel.setFavorite(arguments?.getBoolean(ARGUMENT_FAVORITE, false) ?: false)
    }

    private fun initView() {
        tvShowAdapter = TvShowAdapter()

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun initAction() {
        tvShowAdapter.itemClickListener = { tvShow ->
            startActivity(TvShowDetailActivity.newIntent(requireContext(), tvShow))
        }

        binding.viewWarning.btnWarning.setOnClickListener {
            tvShowViewModel.refresh()
        }
    }

    private fun getTvShows() {
        tvShowViewModel.getTvShows().observe(requireActivity()) {
            tvShowAdapter.submitList(it)
            tvShowAdapter.notifyDataSetChanged()
        }

        tvShowViewModel.tvShowState.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> showData()
                Resource.Status.EMPTY_DATA -> showEmptyData(it.message.toString())
                Resource.Status.ERROR -> showWarning(it.message.toString())
            }
        }
    }

    private fun showLoading() {
        binding.wrapperContent.gone()
        binding.wrapperWarning.gone()
        binding.progressBar.visible()
    }

    private fun showData() {
        binding.wrapperContent.visible()
        binding.wrapperWarning.gone()
        binding.progressBar.gone()
    }

    private fun showEmptyData(message: String) {
        binding.wrapperContent.gone()
        binding.viewWarning.btnWarning.gone()
        binding.wrapperWarning.visible()
        binding.progressBar.gone()

        binding.viewWarning.tvWarning.text = message
    }

    private fun showWarning(message: String) {
        binding.wrapperContent.gone()
        binding.viewWarning.btnWarning.visible()
        binding.wrapperWarning.visible()
        binding.progressBar.gone()

        binding.viewWarning.tvWarning.text = message
    }

    @Subscribe
    fun onReceiveEventBus(event: FavoriteEvent) {
        if (event.type != Const.TV_SHOW_TYPE) return
        if (!event.changes) return
        if (!tvShowViewModel.favoritePage) return

        tvShowViewModel.refresh()
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}