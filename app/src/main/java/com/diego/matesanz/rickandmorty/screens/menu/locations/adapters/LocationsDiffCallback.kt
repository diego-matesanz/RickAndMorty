package com.diego.matesanz.rickandmorty.screens.menu.characters.adapters

import androidx.recyclerview.widget.DiffUtil
import com.diego.matesanz.rickandmorty.data.model.Character

class CharactersDiffCallback(
    private val oldCharacterList: List<Character>,
    private val newCharacterList: List<Character>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldCharacterList.size
    override fun getNewListSize(): Int = newCharacterList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCharacterList[oldItemPosition].id == newCharacterList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCharacterList[oldItemPosition].name == newCharacterList[newItemPosition].name &&
                oldCharacterList[oldItemPosition].image == newCharacterList[newItemPosition].image &&
                oldCharacterList[oldItemPosition].species == newCharacterList[newItemPosition].species &&
                oldCharacterList[oldItemPosition].status == newCharacterList[newItemPosition].status
    }
}