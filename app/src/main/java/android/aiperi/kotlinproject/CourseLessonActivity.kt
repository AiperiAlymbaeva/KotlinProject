package android.aiperi.kotlinproject

import android.aiperi.kotlinproject.adapter.CourseDetailAdapter
import android.aiperi.kotlinproject.adapter.CourseLessonViewHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_course_lesson.*

class CourseLessonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_lesson)

        val courseLink = intent.getStringExtra(CourseLessonViewHolder.COURSE_LESSON_LINK_KEY)

        webview_course_lesson.settings.javaScriptEnabled = true
        webview_course_lesson.settings.loadWithOverviewMode = true
        webview_course_lesson.settings.useWideViewPort = true

        if (courseLink != null) {
            webview_course_lesson.loadUrl(courseLink)
        }
    }
}