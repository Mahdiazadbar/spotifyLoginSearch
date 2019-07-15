package com.azadbar.digipaychallenge.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azadbar.digipaychallenge.R
import com.squareup.picasso.Picasso
import com.azadbar.digipaychallenge.model.ArtistItems
import kotlinx.android.synthetic.main.item.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<ArtistItems>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(p0.context)
                .inflate(R.layout.item, p0, false)
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ViewHolder).setInfo(items[p1])

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setInfo(item: ArtistItems) {
            itemView.name.text = item.name
            itemView.type.text = item.type
            itemView.popularity.text = item.popularity.toString()
            if (item.images.isNotEmpty()) {
                setImage(item.images[(item.images.size - 1)].url)
            } else {
                itemView.img.setImageResource(R.mipmap.ic_launcher)

            }
        }

        private fun setImage(url: String) {
            Picasso
                    .with(itemView.context)
                    .load(url)
                    .into(itemView.img)

        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}