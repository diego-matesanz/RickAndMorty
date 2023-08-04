package com.diego.matesanz.rickandmorty.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.diego.matesanz.rickandmorty.R
import com.diego.matesanz.rickandmorty.data.model.Character
import com.diego.matesanz.rickandmorty.data.model.Location
import com.diego.matesanz.rickandmorty.screens.menu.characters.adapters.CharacterItemAdapter
import com.diego.matesanz.rickandmorty.screens.menu.locations.adapters.LocationItemAdapter

object GlobalBindingAdapters {

    @BindingAdapter("image")
    @JvmStatic
    fun setImage(imageView: ImageView, image: String) {
        val imageOptions = RequestOptions()
            .centerCrop()
            .override(600, 600)
            .error(R.drawable.rick_and_morty_place_holder)
            .placeholder(R.drawable.rick_and_morty_place_holder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        Glide.with(imageView.context)
            .load(image)
            .apply(imageOptions)
            .into(imageView)
    }

    @BindingAdapter("fullImage")
    @JvmStatic
    fun setFullImage(imageView: ImageView, fullImage: String?) {
        val imageOptions = RequestOptions()
            .centerCrop()
            .error(R.drawable.rick_and_morty_place_holder)
            .placeholder(R.drawable.rick_and_morty_place_holder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        Glide.with(imageView.context)
            .load(fullImage)
            .apply(imageOptions)
            .into(imageView)
    }

    @BindingAdapter("species", "status")
    @JvmStatic
    fun setCharacterInfo(textView: TextView, species: String, status: String) {
        textView.text = textView.context.getString(R.string.character_info, species, status)
    }

    @BindingAdapter("characters")
    @JvmStatic
    fun setCharacters(recyclerView: RecyclerView, characters: MutableList<Character>?) {
        val adapter = recyclerView.adapter
        if (adapter is CharacterItemAdapter) {
            if (characters != null) {
                val charactersCopy = characters.map { it.copy() } as MutableList<Character>
                adapter.setCharacters(charactersCopy)
            }
        }
    }

    @BindingAdapter("locations")
    @JvmStatic
    fun setLocations(recyclerView: RecyclerView, locations: MutableList<Location>?) {
        val adapter = recyclerView.adapter
        if (adapter is LocationItemAdapter) {
            if (locations != null) {
                val locationsCopy = locations.map { it.copy() } as MutableList<Location>
                adapter.setLocations(locationsCopy)
            }
        }
    }

    @BindingAdapter("searchText")
    @JvmStatic
    fun setErrorBody(textView: TextView, searchText: String) {
        textView.text = textView.context.getString(R.string.error_body, searchText)
    }
}
