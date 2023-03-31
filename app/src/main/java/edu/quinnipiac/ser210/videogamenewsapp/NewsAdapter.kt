package edu.quinnipiac.ser210.videogamenewsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.quinnipiac.ser210.videogamenewsapp.databinding.NewsItemBinding

class NewsAdapter(var newsItems: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsItems[position]
        holder.binding.newsItemTitle.text = currentItem.title
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.binding.newsItemImage)
    }

    override fun getItemCount() = newsItems.size
}
