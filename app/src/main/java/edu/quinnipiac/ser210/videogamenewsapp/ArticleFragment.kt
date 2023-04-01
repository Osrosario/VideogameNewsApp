package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment()
{
    var article_id: Int = 0
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding !!


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val bundle = arguments

        if (bundle == null)
        {
            Log.e("DetailFragment", "DetailFragment did not receive hero id")

            return
        }

        article_id = ArticleFragmentArgs.fromBundle(bundle).pos
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = newsList.get(article_id).title
        binding.date.text = newsList.get(article_id).date
        binding.description.text = newsList.get(article_id).description
        binding.link.text = newsList.get(article_id).link
        Glide.with(requireContext()).load(newsList.get(article_id).image)
            .apply(RequestOptions().centerCrop())
            .into(binding.image)
    }
}