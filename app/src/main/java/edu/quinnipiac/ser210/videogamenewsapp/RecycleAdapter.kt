package edu.quinnipiac.ser210.videogamenewsapp

import android.content.Context
import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//var newsList : ArrayList<Article> = ArrayList()

class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private var news: List<News> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.item_title)

        //private val dateView: TextView = itemView.findViewById(R.id.item_date)
        private val imageView: ImageView = itemView.findViewById(R.id.item_image)
        //private val descriptionView: TextView = itemView.findViewById(R.id.item_description)

        fun bind(item: News) {
            titleView.text = item.title
            //dateView.text = item.date
            //descriptionView.text = item.description

            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size

    fun updateNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

}

/*
class RecyclerAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int
    {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.bind(position)
    }

    fun setNewsListItems(newsListparam: ArrayList<Article>)
    {
        newsList = newsListparam;
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view)
    {
        private val title: TextView = view.findViewById(R.id.item_title)
        private val image: ImageView = view.findViewById(R.id.item_image)
        private var pos:Int = 0

        init {
            itemView.setOnClickListener {

                val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(pos)
                navController.navigate(action)

            }
        }

        fun bind(position: Int)
        {
            pos = position
            val currNews = newsList.get(position)
            title.text = currNews.title

            Glide.with(context).load(currNews.image)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }
    }
}

 */