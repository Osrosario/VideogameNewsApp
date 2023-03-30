package edu.quinnipiac.ser210.videogamenewsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface
{

        @Headers(
                "X-RapidAPI-Key: f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861",
                "X-RapidAPI-Host: videogames-news2.p.rapidapi.com"
        )
        @GET("videogames_news/recent")
        suspend fun getNews(): List<News>

    /*
    fun getNews(): Call<ArrayList<Article?>?>?

    companion object
    {
        var BASE_URL = "https://videogames-news2.p.rapidapi.com/videogames_news/"

        fun create(): ApiInterface
        {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

     */
}