package com.diego.matesanz.rickandmorty.screens.menu.characters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.databinding.LayoutCharacterItemBinding

class CharacterItemAdapter(private var characters: MutableList<Character>) : RecyclerView.Adapter<CharacterItemAdapter
.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_character_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.apply {
            binding.character = character
        }
    }

    fun setCharacters(newList: MutableList<Character>) {
        val charactersDiffCallback = CharactersDiffCallback(characters, newList)
        val diffResult = DiffUtil.calculateDiff(charactersDiffCallback)
        characters = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutCharacterItemBinding.bind(view)
    }
}
