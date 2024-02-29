package com.techjet.codingtask.view.list

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.techjet.codingtask.apiclient.ApiError
import com.techjet.codingtask.apiclient.RetrofitClient
import com.techjet.codingtask.apiclient.list.ListApi
import com.techjet.codingtask.base.adapter.ViewModelItem
import com.techjet.codingtask.model.list.Item
import com.techjet.codingtask.model.list.ListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class ListRepository {

    @JvmField
    val mAdapter = ObservableField(ListAdapter(ArrayList()))

    init {
        fetchList("cat", 1)
    }

    private fun fetchList(tags: String, callback: Int) {
        val api = ListApi(tags, callback)
        api.call(object : RetrofitClient.Listener<ListResponse, ApiError> {

            override fun onResponse(response: ListResponse) {
                val items: List<Item>? = response.items
                if (!items.isNullOrEmpty()) {

                    Collections.sort(items, ItemComparator())

                    val list = ArrayList<ViewModelItem>()
                    for (item in items) {
                        list.add(ListItemViewModel(item))
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        mAdapter.get()?.addItems(list)
                    }
                }
            }

            override fun onError(error: ApiError) {

            }

        })
    }

    private class ItemComparator : Comparator<Item> {

        override fun compare(o1: Item?, o2: Item?): Int {
            o1?.let { item1 ->
                o2?.let { item2 ->
                    if (!TextUtils.isEmpty(item1.published) && !TextUtils.isEmpty(item2.published)) {
                        return item2.published.compareTo(item1.published)
                    }
                }
            }

            return 0
        }

    }

}