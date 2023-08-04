package com.diego.matesanz.rickandmorty.screens.menu.episodes.adapters

import androidx.recyclerview.widget.DiffUtil
import com.diego.matesanz.rickandmorty.data.model.Episode
import com.diego.matesanz.rickandmorty.data.model.Location

class EpisodesDiffCallback(
    private val oldEpisodeList: List<Episode>,
    private val newEpisodeList: List<Episode>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldEpisodeList.size
    override fun getNewListSize(): Int = newEpisodeList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldEpisodeList[oldItemPosition].id == newEpisodeList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldEpisodeList[oldItemPosition].name == newEpisodeList[newItemPosition].name
    }
}
