package com.example.opeyemiabdulsalam.carowners

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.databinding.ItemCarOwnerBinding

class CarOwnerAdapter: ListAdapter<CarOwner, CarOwnerAdapter.CarOwnerViewHolder>(DiffCallBack()) {

    class CarOwnerViewHolder(private val binding: ItemCarOwnerBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: CarOwner){
            binding.carOwner = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CarOwnerViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemCarOwnerBinding.inflate(inflater, parent, false)

                return CarOwnerViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarOwnerViewHolder {
        return CarOwnerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CarOwnerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallBack: DiffUtil.ItemCallback<CarOwner>(){
    override fun areItemsTheSame(oldItem: CarOwner, newItem: CarOwner): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarOwner, newItem: CarOwner): Boolean {
        return oldItem == newItem
    }

}