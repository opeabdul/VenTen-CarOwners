package com.example.opeyemiabdulsalam.filterlist

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.opeyemiabdulsalam.data.Filter
import com.example.opeyemiabdulsalam.databinding.ItemFilterBinding

class FilterAdapter (private val clickListener: FilterClickListener):
    ListAdapter<Filter, FilterAdapter.FilterViewHolder>(FilterDiffCallback()) {

    class FilterViewHolder private constructor(val binding: ItemFilterBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: FilterClickListener, item: Filter){
            binding.filter = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FilterViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding  = ItemFilterBinding.inflate(layoutInflater, parent, false)

                return FilterViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}

class FilterDiffCallback: DiffUtil.ItemCallback<Filter>(){
    override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
        return oldItem == newItem
    }

}

class FilterClickListener(val clickListener: (filter: Filter) -> Unit){
    fun onClick(filter: Filter) = clickListener(filter)
}