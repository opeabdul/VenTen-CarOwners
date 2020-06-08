package com.example.opeyemiabdulsalam

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opeyemiabdulsalam.data.Filter
import com.example.opeyemiabdulsalam.filterlist.FilterAdapter

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Filter>?) {
    val adapter = recyclerView.adapter as FilterAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
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