package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private lateinit var article: NewsItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // to pass news *doesn't currently work*
        arguments?.let {
            article = it.getParcelable("article") ?: NewsItem("", "", "", "","")
            binding.articleTitle.text = article.title
            binding.articleDate.text = article.date
            Glide.with(requireContext())
                .load(article.image)
                .into(binding.articleImage)
            binding.articleDescription.text = article.description
            binding.articleLink.text = article.link
        }
    }
}

