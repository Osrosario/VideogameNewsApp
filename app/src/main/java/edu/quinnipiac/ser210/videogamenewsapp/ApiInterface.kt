package edu.quinnipiac.ser210.videogamenewsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/3/2023
 *
 * API Interface to access "Videogames NEWS" data from RapidAPI. Retrieves a random selection
 * of video game news articles using the "recent" endpoint and stores them into an ArrayList.
 */
interface ApiInterface
{
    @Headers(
        "x-rapidapi-key: f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861",
        "x-rapidapi-host: videogames-news2.p.rapidapi.com"
    )

    @GET("videogames_news/recent")
    fun getRecentNews(): Call<ArrayList<Article?>?>?

    companion object
    {
        fun create(): ApiInterface
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://videogames-news2.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}