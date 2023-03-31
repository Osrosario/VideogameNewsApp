package edu.quinnipiac.ser210.videogamenewsapp

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.quinnipiac.ser210.videogamenewsapp.databinding.NewsItemBinding

class NewsAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.news_item_title)
        val itemDate: TextView = itemView.findViewById(R.id.news_item_date)
        val itemImage: ImageView = itemView.findViewById(R.id.news_item_image)
        val itemDescription: TextView = itemView.findViewById(R.id.news_item_description)
        val itemLink: TextView = itemView.findViewById(R.id.news_item_link)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        val sendableTitle = currentItem.title
        val sendableDate = currentItem.date
        val sendableDescription = currentItem.description
        val sendableLink = currentItem.link
        val sendableImage = currentItem.image

        holder.itemTitle.text = currentItem.title
        holder.itemDate.text = currentItem.date
        holder.itemDescription.text = currentItem.description
        holder.itemLink.text = currentItem.link

        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.itemImage)

        holder.itemView.setOnClickListener {

            val bundle = Bundle().apply {
                putString("title", sendableTitle)
                putString("date", sendableDate)
                putString("link", sendableLink)
                putString("description", sendableDescription)
                putString("image", sendableImage)
            }

            Log.d("Bundle contents", bundle.toString())

            it.findNavController().navigate(R.id.action_newsFragment_to_articleFragment)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}

