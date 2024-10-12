package com.example.webtooninfo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webtooninfo.R
import com.example.webtooninfo.data.Webtoon

class WebtoonAdapter(private var webtoons: List<Webtoon>, private val onItemClick: (Webtoon) -> Unit) :
    RecyclerView.Adapter<WebtoonAdapter.WebtoonViewHolder>() {

    class WebtoonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.webtoon_image)
        val title: TextView = view.findViewById(R.id.webtoon_title)
        val description: TextView = view.findViewById(R.id.webtoon_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebtoonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_webtoon, parent, false)
        return WebtoonViewHolder(view)
    }

    override fun onBindViewHolder(holder: WebtoonViewHolder, position: Int) {
        val webtoon = webtoons[position]
        holder.title.text = webtoon.title
        holder.description.text = webtoon.description
        Glide.with(holder.image.context).load(webtoon.imageUrl).into(holder.image)
        holder.itemView.setOnClickListener { onItemClick(webtoon) }
    }

    override fun getItemCount() = webtoons.size

    // Add the updateWebtoons method
    fun updateWebtoons(newWebtoons: List<Webtoon>) {
        webtoons = newWebtoons
        notifyDataSetChanged()
    }
}
