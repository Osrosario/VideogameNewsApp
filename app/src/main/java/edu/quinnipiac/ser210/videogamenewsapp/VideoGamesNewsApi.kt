package edu.quinnipiac.ser210.videogamenewsapp
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VideoGamesNewsApi(private val context: Context) {

    private val BaseUrl = "https://videogames-news2.p.rapidapi.com"
    private val ApiKey = "f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861"

    data class News(
        val title: String,
        val author: String,
        val link: String,
        val pubDate: String,
        val description: String
    )

    fun getNews(callback: (List<News>) -> Unit) {
        val endpoint = "/api/games"
        val url = BaseUrl + endpoint

        val request = object : JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val gson = Gson()
                val newsList = gson.fromJson<List<News>>(
                    response.toString(),
                    object : TypeToken<List<News>>() {}.type
                )
                callback(newsList)
            },
            Response.ErrorListener { error ->
                Log.e("VideoGamesNewsApi", error.toString())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-rapidapi-host"] = "videogames-news2.p.rapidapi.com"
                headers["x-rapidapi-key"] = ApiKey
                return headers
            }
        }

        Volley.newRequestQueue(context).add(request)
    }
}
