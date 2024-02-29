package com.techjet.codingtask.view.list

import android.annotation.SuppressLint
import com.techjet.codingtask.R
import com.techjet.codingtask.base.adapter.DataBindingRecyclerViewAdapter
import com.techjet.codingtask.base.adapter.ViewModelItem

class ListAdapter(viewModels: MutableList<ViewModelItem>) : DataBindingRecyclerViewAdapter(viewModels) {

    private val mViewModelMap = HashMap<Class<*>, Int>()

    init {
        mViewModelMap[ListItemViewModel::class.java] = R.layout.list_item
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(itemList: MutableList<ViewModelItem>) {
        mViewModels = itemList
        notifyDataSetChanged()
    }

    fun addItems(itemList: MutableList<ViewModelItem>) {
        val startPosition = mViewModels.size
        mViewModels.addAll(itemList)
        notifyItemRangeInserted(startPosition, mViewModels.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        mViewModels.clear()
        notifyDataSetChanged()
    }

    override val viewModelLayoutMap: Map<Class<*>, Int>
        get() = mViewModelMap

}