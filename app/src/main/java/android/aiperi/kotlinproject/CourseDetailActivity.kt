package android.aiperi.kotlinproject

import android.aiperi.kotlinproject.adapter.CourseDetailAdapter
import android.aiperi.kotlinproject.adapter.CustomViewHolder
import android.aiperi.kotlinproject.models.CourseLesson
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_main.layoutManager = LinearLayoutManager(this)
//recyclerview_main.adapter = CourseDetailAdapter()
        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        fetchJSON()
    }

    private fun fetchJSON() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId


        val client = OkHttpClient()
        val request = Request.Builder()
            .url(courseDetailUrl)
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response?.body?.string()

                    val gson = GsonBuilder().create()

                    val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)

                    runOnUiThread {
                        recyclerview_main.adapter = CourseDetailAdapter(courseLessons)
                    }


                }
                override fun onFailure(call: Call, e: IOException) {

                }

            })
    }
}

