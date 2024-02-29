package com.techjet.codingtask.utils.bindingUtils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingUtil {

    private const val SET_ADAPTER = "setAdapter"

    @JvmStatic
    @BindingAdapter(SET_ADAPTER)
    fun <T : RecyclerView.ViewHolder?> setAdapter(
        recyclerView: RecyclerView?,
        adapter: RecyclerView.Adapter<T>?
    ) {
        if (recyclerView != null && adapter != null) {
            recyclerView.adapter = adapter
        }
    }

}