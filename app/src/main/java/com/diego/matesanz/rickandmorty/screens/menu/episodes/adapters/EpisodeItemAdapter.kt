package com.diego.matesanz.rickandmorty.screens.menu.episodes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.data.model.Episode
import com.diego.matesanz.rickandmorty.databinding.LayoutEpisodeItemBinding
import com.diego.matesanz.rickandmorty.interfaces.OnCardClickListener

class EpisodeItemAdapter(private var episodes: MutableList<Episode>, private val listener: OnCardClickListener) :
    RecyclerView
    .Adapter<EpisodeItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_episode_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        holder.apply {
            binding.episode = episode
            binding.root.setOnClickListener {
                listener.onCardClicked(episode)
            }
        }
    }

    fun setEpisodes(newList: MutableList<Episode>) {
        val episodesDiffCallback = EpisodesDiffCallback(episodes, newList)
        val diffResult = DiffUtil.calculateDiff(episodesDiffCallback)
        episodes = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutEpisodeItemBinding.bind(view)
    }
}
