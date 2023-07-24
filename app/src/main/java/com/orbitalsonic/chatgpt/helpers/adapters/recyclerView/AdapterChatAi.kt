package com.orbitalsonic.chatgpt.helpers.adapters.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orbitalsonic.chatgpt.R
import com.orbitalsonic.chatgpt.databinding.ItemChatAiBinding
import com.orbitalsonic.chatgpt.helpers.interfaces.OnChatItemClickListener
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable

class AdapterChatAi(private val onChatItemClickListener: OnChatItemClickListener) :
    ListAdapter<ChatTable, AdapterChatAi.CustomViewHolder>(diffUtilChatTable) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemChatAiBinding>(
            layoutInflater,
            R.layout.item_chat_ai,
            parent,
            false
        )
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            item = currentItem
            itemClick = onChatItemClickListener
        }
    }

    inner class CustomViewHolder(val binding: ItemChatAiBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffUtilChatTable = object : DiffUtil.ItemCallback<ChatTable>() {
            override fun areItemsTheSame(oldItem: ChatTable, newItem: ChatTable): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ChatTable, newItem: ChatTable): Boolean {
                return oldItem == newItem
            }
        }
    }
}