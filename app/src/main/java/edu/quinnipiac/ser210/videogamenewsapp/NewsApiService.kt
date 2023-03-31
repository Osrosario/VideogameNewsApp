import edu.quinnipiac.ser210.videogamenewsapp.NewsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApiService {

    @Headers(
        "x-rapidapi-key: f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861",
        "x-rapidapi-host: videogames-news2.p.rapidapi.com"
    )
    @GET("videogames_news/recent")
    fun getRecentNews(): Call<List<NewsItem>>
}