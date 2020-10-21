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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val course = listTvShows[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listTvShows.size


    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: TvShowVR) {
            with(itemView) {
                tv_title.text = course.title
                tv_description.text = course.overview
                tv_date.text = course.overview
                setOnClickListener {
//                    val intent = Intent(context, DetailCourseActivity::class.java).apply {
//                        putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
//                    }
//                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(course.image)
//                    .apply(
//                        RequestOptions.placeholderOf(R.drawable.ic_loading)
//                            .error(R.drawable.ic_error))
                    .into(iv_poster)
            }
        }
    }
}