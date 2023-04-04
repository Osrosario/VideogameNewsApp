package edu.quinnipiac.ser210.videogamenewsapp

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.MaterialToolbar

var newsList : ArrayList<Article> = ArrayList()

class RecyclerAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item,parent,false)
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

    fun setHerosListItems(newsListparam: ArrayList<Article>)
    {
        newsList = newsListparam;
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView)
    {
        private val title: TextView = itemView!!.findViewById(R.id.item_title)
        private val image: ImageView = itemView!!.findViewById(R.id.item_image)
        private var pos:Int = 0

        init
        {
            itemView.setOnClickListener {

                val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(pos)
                navController.navigate(action)

                val activity = itemView.context as AppCompatActivity
                val toolbar = activity.findViewById<MaterialToolbar>(R.id.materialToolbar)
                val menuItem = toolbar.menu.findItem(R.id.shareLink)
                menuItem.isVisible = !menuItem.isVisible

            }
        }

        fun bind(position: Int)
        {
            pos = position
            val currArticle = newsList.get(position)
            title.text = currArticle.title

            Glide.with(context).load(currArticle.image)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }
    }
}