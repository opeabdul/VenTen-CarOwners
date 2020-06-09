package com.example.opeyemiabdulsalam

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opeyemiabdulsalam.carowners.CarOwnerAdapter
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.data.Filter
import com.example.opeyemiabdulsalam.filterlist.FilterAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Filter>?) {
    val adapter = recyclerView.adapter as FilterAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("carOwnersData")
fun bindCarRecyclerView(recyclerView: RecyclerView, data: List<CarOwner>?) {
    val adapter = recyclerView.adapter as CarOwnerAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("carEmptyState")
fun showEmptyCarOwnersState(cardView: CardView, data: List<CarOwner>?){
    cardView.visibility = when(data){
        null -> View.GONE
        else -> if(data.isEmpty()) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("barVisibility")
fun showProgressbar(view: View, data: List<CarOwner>?){
    if (data == null)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("countText")
fun bindCount(textView: TextView, count: Int){
    textView.text = String.format("#%d", count)
}

@BindingAdapter("dateRangeText")
fun bindDateRange(textView: TextView, filter: Filter){
    textView.text = String.format("%d - %d", filter.startYear, filter.endYear)
}

@BindingAdapter("textList")
fun bindList(textView: TextView, textList: List<String>){
    var value: String = ""
    for (text in textList){
         value = "$value $text"
    }
    textView.text = when(value){
        "" -> "Not available"
        else -> value.trim()
    }
}