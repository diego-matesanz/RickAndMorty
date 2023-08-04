package com.diego.matesanz.rickandmorty.screens.menu.locations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.data.model.Location
import com.diego.matesanz.rickandmorty.databinding.LayoutLocationItemBinding
import com.diego.matesanz.rickandmorty.interfaces.OnCardClickListener

class LocationItemAdapter(private var locations: MutableList<Location>, private val listener: OnCardClickListener) :
    RecyclerView
    .Adapter<LocationItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_location_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.apply {
            binding.location = location
            binding.root.setOnClickListener {
                listener.onCardClicked(location)
            }
        }
    }

    fun setLocations(newList: MutableList<Location>) {
        val charactersDiffCallback = LocationsDiffCallback(locations, newList)
        val diffResult = DiffUtil.calculateDiff(charactersDiffCallback)
        locations = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutLocationItemBinding.bind(view)
    }
}
