package edu.quinnipiac.ser210.videogamenewsapp

import NewsApiService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter(emptyList())
        binding.newsRecyclerView.adapter = newsAdapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)

        // retrofit for api
        val retrofit = Retrofit.Builder()
            .baseUrl("https://videogames-news2.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)
        service.getRecentNews().enqueue(object : Callback<List<NewsItem>> {
            override fun onResponse(
                call: Call<List<NewsItem>>,
                response: Response<List<NewsItem>>
            ) {
                if (response.isSuccessful) {
                    val newsItems = response.body() ?: emptyList()
                    newsAdapter = NewsAdapter(newsItems)
                    binding.newsRecyclerView.adapter = newsAdapter

                } else {
                    Log.e("NewsFragment", "Failed to get news: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<NewsItem>>, t: Throwable) {
                Log.e("NewsFragment", "Error getting news", t)
            }
        })
    }
}
