package android.aiperi.kotlinproject

import android.aiperi.kotlinproject.adapter.MainAdapter
import android.aiperi.kotlinproject.models.HomeFeed
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerview_main.layoutManager = LinearLayoutManager(this)


        fetchJson()
    }

    private fun fetchJson() {
        println("Attempting to Fetch JSON")
    val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

    val request = Request.Builder()
            .url(url)
            .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: okhttp3.Call, response: Response) {
            val body = response.body?.string()
            println(body)

            val gson = GsonBuilder().create()
            val homeFeed = gson.fromJson(body, HomeFeed:: class.java)

            runOnUiThread {
                recyclerview_main.adapter =
                    MainAdapter(homeFeed)
            }
        }
        override fun onFailure(call: okhttp3.Call, e: IOException) {

            println("Failed to execute request")
        }
    })
}
}






