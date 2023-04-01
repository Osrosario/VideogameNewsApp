package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import edu.quinnipiac.ser210.videogamenewsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedNewsItem = viewModel.selectedNewsItem
        if (selectedNewsItem != null) {
            Glide.with(this).load(selectedNewsItem.image).into(binding.articleImage)
            binding.articleTitle.text = selectedNewsItem.title
            binding.articleDate.text = selectedNewsItem.date
            binding.articleDescription.text = selectedNewsItem.description
            binding.articleLink.text = selectedNewsItem.link
        }

    }

}

