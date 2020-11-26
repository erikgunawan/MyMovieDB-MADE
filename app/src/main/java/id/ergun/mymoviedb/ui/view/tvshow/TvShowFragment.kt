package id.ergun.mymoviedb.ui.view.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.databinding.TvShowFragmentBinding
import id.ergun.mymoviedb.ui.viewmodel.tvshow.TvShowViewModel
import id.ergun.mymoviedb.util.Resource
import id.ergun.mymoviedb.util.autoCleared
import id.ergun.mymoviedb.util.gone
import id.ergun.mymoviedb.util.visible

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var binding: TvShowFragmentBinding by autoCleared()

    private val tvShowViewModel by viewModels<TvShowViewModel>()

    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAction()
        getTvShows()
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
        binding.viewWarning.btnWarning.setOnClickListener {
            getTvShows()
        }
    }

    private fun getTvShows() {
        showLoading()
        tvShowViewModel.getTvShows().observe(requireActivity(), {
            showData()
            if (it.status == Resource.Status.SUCCESS) {
                tvShowAdapter.setTvShows(it.data?.let { it1 -> TvShowVR.transform(it1) })
                tvShowAdapter.notifyDataSetChanged()
                return@observe
            }
            if(it.status == Resource.Status.ERROR) {
                binding.viewWarning.tvWarning.text = it.message
            }
            showWarning()
        })
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

    private fun showWarning() {
        binding.wrapperContent.gone()
        binding.wrapperWarning.visible()
        binding.progressBar.gone()
    }
}
