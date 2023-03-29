package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment()
{
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
}