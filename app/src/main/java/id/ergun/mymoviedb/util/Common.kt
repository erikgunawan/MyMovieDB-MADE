package id.ergun.mymoviedb.util

import android.app.Activity
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.R

/**
 * Created by alfacart on 22/10/20.
 */

fun ImageView.loadImage(
    @DrawableRes url: Int?,
    errorDrawable: Int = R.drawable.ic_image_placeholder_error,
    progressDrawable: Int? = null
) {
    val requestOptions = RequestOptions().build(errorDrawable, progressDrawable)

    val builder = Glide.with(this.context)
        .load(url)
        .apply(requestOptions)

    builder.into(this)
}

fun RequestOptions.build(
    errorDrawable: Int = R.drawable.ic_image_placeholder_error,
    progressDrawable: Int? = null): RequestOptions {
    with(this) {
        error(errorDrawable)
        if (progressDrawable != null) this.placeholder(progressDrawable)
    }
    return this
}

fun share(activity: Activity, type: String, id: String) {

    val title = when (type) {
        "tv" -> "Bagikan TV Show ini"
        "movie" -> "Bagikan Movie ini"
        else -> "Bagikan"
    }

    val mimeType = "text/plain"
    ShareCompat.IntentBuilder.from(activity).apply {
        setType(mimeType)
        setChooserTitle(title)
        setText("https://www.themoviedb.org/${type}/${id}")
        startChooser()
    }
}
