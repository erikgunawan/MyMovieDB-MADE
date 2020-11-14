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
import id.ergun.mymoviedb.util.autoCleared

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var binding: TvShowFragmentBinding by autoCleared()

    private val tvShowViewModel by viewModels<TvShowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity==null) return

        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.setTvShows(TvShowVR.transform(tvShowViewModel.getTvShows()))

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}
