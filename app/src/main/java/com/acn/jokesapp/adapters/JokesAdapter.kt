package com.acn.jokesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acn.jokesapp.R
import com.acn.jokesapp.data.model.JokeItem
import kotlinx.android.synthetic.main.joke_item.view.*

class JokesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<JokeItem> = ArrayList()

    fun submitList(jokesList: List<JokeItem>) {
        items = jokesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.joke_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BlogViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jokeItem: JokeItem) {
            itemView.jokeText.text = jokeItem.joke
        }
    }

}