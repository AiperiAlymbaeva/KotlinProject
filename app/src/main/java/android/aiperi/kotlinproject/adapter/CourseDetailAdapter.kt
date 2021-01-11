package android.aiperi.kotlinproject.adapter

import android.aiperi.kotlinproject.CourseLessonActivity
import android.aiperi.kotlinproject.R
import android.aiperi.kotlinproject.models.CourseLesson
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course_detail.view.*

class CourseDetailAdapter(val courseLessons: Array<CourseLesson>) :
    RecyclerView.Adapter<CourseLessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.activity_course_detail, parent, false)
        return CourseLessonViewHolder(customView)
    }

    override fun getItemCount(): Int {
        return courseLessons.size;
    }

    override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
        val courseLesson = courseLessons.get(position)

        holder?.customView?.textView_course_name?.text = courseLesson.name
        holder?.customView?.textView_episode?.text = courseLesson.duration

        val imageView = holder?.customView?.imageView_course
        Glide.with(holder?.customView?.context)
            .load(courseLesson.imageUrl)
            .into(imageView)

        holder?.courseLesson = courseLesson
    }


}

class CourseLessonViewHolder(val customView: View, var courseLesson: CourseLesson? = null) :
    RecyclerView.ViewHolder(customView) {
    companion object {
        val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"
    }

    init {
        customView.setOnClickListener {
            println("Attempt to load webview somehow???")

            val intent = Intent(customView.context, CourseLessonActivity::class.java)

            intent.putExtra(COURSE_LESSON_LINK_KEY, courseLesson?.link)

            customView.context.startActivity(intent)
        }
    }
}