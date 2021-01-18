package id.ergun.mymoviedb.core.view.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.ergun.mymoviedb.core.BuildConfig
import id.ergun.mymoviedb.core.databinding.TvShowItemsBinding
import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.util.loadImage

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowAdapter : PagedListAdapter<TvShowVR, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: ((tvShow: TvShow) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowVR>() {
            override fun areItemsTheSame(oldItem: TvShowVR, newItem: TvShowVR): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowVR, newItem: TvShowVR): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(
            TvShowItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class TvShowViewHolder(private val binding: TvShowItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowVR) {
            binding.tvTitle.text = tvShow.title
            binding.viewRating.tvRating.text = tvShow.voteAverage.toString()
            binding.tvOverview.text = tvShow.overview
            binding.ivPoster.loadImage(BuildConfig.IMAGE_URL + tvShow.posterPath)
            itemView.setOnClickListener {
                itemClickListener?.invoke(TvShowVR.toModel(tvShow))
            }
        }
    }
}