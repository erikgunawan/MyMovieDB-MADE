package id.ergun.mymoviedb.ui.view.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.view.movie.MovieVR
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.ui.view.tvshow.detail.TvShowDetailActivity
import id.ergun.mymoviedb.util.loadImage
import kotlinx.android.synthetic.main.movie_items.view.*

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<TvShowVR>()

    fun setTvShows(movies: List<TvShowVR>?) {
        if (movies == null) return
        listTvShows.clear()
        listTvShows.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_show_items, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size


    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowVR) {
            with(itemView) {
                tv_title.text = tvShow.title
                tv_rating.text = tvShow.voteAverage.toString()
                tv_tagline.text = tvShow.tagline
                setOnClickListener {
                    val intent = TvShowDetailActivity.newIntent(context, TvShowVR.toModel(tvShow))
                    context.startActivity(intent)
                }
                iv_poster.loadImage(tvShow.image)
            }
        }
    }
}