package android.aiperi.kotlinproject.adapter

import android.aiperi.kotlinproject.CourseDetailActivity
import android.aiperi.kotlinproject.R
import android.aiperi.kotlinproject.models.HomeFeed
import android.aiperi.kotlinproject.models.Video
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val callForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(callForRow)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        holder?.view?.textView_video?.text = video.name

        holder.view.channel_name_tv.text = video.channel.name

        val thumbnailImageView = holder.view.imageView_video
        Glide.with(holder.view.context)
            .load(video.imageUrl)
            .into(thumbnailImageView)

        val profileChannelImageView = holder.view.avatar
        Glide.with(holder.view.context)
            .load(video.channel.profileImageUrl)
            .into(profileChannelImageView)

        holder?.video = video
    }


}

class CustomViewHolder(val view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {
    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"
    }
    init {
        view.setOnClickListener() {
            val intent = Intent(view.context, CourseDetailActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            view.context.startActivity(intent)
        }
    }

}