package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecycleAdapter

    private val newsApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://videogames-news2.p.rapidapi.com/videogames_news/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = RecycleAdapter()
        recyclerView.adapter = recyclerAdapter

        loadNews()

        return view
    }

    private fun loadNews() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val news = newsApi.getNews()
                recyclerAdapter.updateNews(news)
            } catch (e: Exception) {
                Toast.makeText(activity, "Error loading news", Toast.LENGTH_SHORT).show()
            }
        }
    }

/*
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecycleAdapter

    private val ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://videogames-news2.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = ApiInterface.getNews().execute()
                if (response.isSuccessful) {
                    val newsList = response.body() ?: emptyList()

                    recyclerAdapter = RecycleAdapter(newsList)
                    recyclerView.adapter = recyclerAdapter
                } else {
                    Toast.makeText(
                        activity,
                        "Failed to fetch news",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    activity,
                    "Failed to fetch news: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }


 */

    /*
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getNews()

        if (apiInterface != null)
        {
            apiInterface.enqueue(object : Callback<ArrayList<Article?>?>
            {
                override fun onResponse(call: Call<ArrayList<Article?>?>, response: Response<ArrayList<Article?>?>)
                {
                    if (response.body() != null)
                    {
                        recyclerAdapter.setNewsListItems(response.body() !! as ArrayList<Article>)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Article?>?>, t: Throwable)
                {
                    if (t != null)
                    {
                        t.message?.let { Log.d("onFailure", it) }
                    }
                }
            })
        }
    }

     */
}