package edu.quinnipiac.ser210.videogamenewsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiInterface
{
    @GET("recent/")
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
}