package com.diego.matesanz.rickandmorty.screens.menu.locations.adapters

import androidx.recyclerview.widget.DiffUtil
import com.diego.matesanz.rickandmorty.data.model.Location

class LocationsDiffCallback(
    private val oldLocationList: List<Location>,
    private val newLocationList: List<Location>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldLocationList.size
    override fun getNewListSize(): Int = newLocationList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLocationList[oldItemPosition].id == newLocationList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLocationList[oldItemPosition].name == newLocationList[newItemPosition].name
    }
}
