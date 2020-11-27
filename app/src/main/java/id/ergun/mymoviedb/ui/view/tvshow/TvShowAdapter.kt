package id.ergun.mymoviedb.ui.view.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.databinding.TvShowItemsBinding
import id.ergun.mymoviedb.ui.view.tvshow.detail.TvShowDetailActivity
import id.ergun.mymoviedb.util.loadImage

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<TvShowVR>()

    fun setTvShows(tvShows: List<TvShowVR>?) {
        if (tvShows.isNullOrEmpty()) return
        listTvShows.clear()
        listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = TvShowItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size

    class TvShowViewHolder(private val binding: TvShowItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowVR) {
            with(itemView) {
                binding.tvTitle.text = tvShow.title
                binding.tvRating.text = tvShow.voteAverage.toString()
                binding.tvTagLine.text = tvShow.tagLine
                setOnClickListener {
                    val intent = TvShowDetailActivity.newIntent(context, TvShowVR.toModel(tvShow))
                    context.startActivity(intent)
                }
                binding.ivPoster.loadImage(BuildConfig.IMAGE_URL + tvShow.posterPath)
            }
        }
    }
}