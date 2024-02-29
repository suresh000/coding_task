package com.techjet.codingtask.view.list

import android.content.Context
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.techjet.codingtask.apiclient.ApiError
import com.techjet.codingtask.apiclient.RetrofitClient
import com.techjet.codingtask.apiclient.list.ListApi
import com.techjet.codingtask.base.adapter.ViewModelItem
import com.techjet.codingtask.model.list.Item
import com.techjet.codingtask.model.list.ListResponse
import com.techjet.codingtask.room.AppRoomDatabase
import com.techjet.codingtask.room.entity.ListItem
import com.techjet.codingtask.utils.NetworkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class ListRepository(private val mContext: Context) {

    @JvmField
    val isLoading = ObservableBoolean(true)

    @JvmField
    val mAdapter = ObservableField(ListAdapter(ArrayList()))

    fun callApi(tags: String, callback: Int) {
        if (NetworkUtil.isConnected(mContext)) {
            fetchList(tags, callback)
        } else {
            Thread {
                val list: MutableList<ListItem> =
                    AppRoomDatabase.getInstance(mContext).listItemDao().getList()
                if (list.isNotEmpty()) {
                    loadOffline(list)
                }
            }.start()
        }
    }

    fun fetchList(tags: String, callback: Int) {
        val api = ListApi(tags, callback)
        api.call(object : RetrofitClient.Listener<ListResponse, ApiError> {

            override fun onResponse(response: ListResponse) {
                val items: List<Item>? = response.items
                if (!items.isNullOrEmpty()) {

                    Collections.sort(items, ItemComparator())

                    val list = ArrayList<ViewModelItem>()
                    val sList = ArrayList<ListItem>()
                    for (item in items) {
                        list.add(ListItemViewModel(item))

                        sList.add(ListItem().apply {
                            title = item.title ?: ""
                            image = item.media?.m ?: ""
                            link = item.link
                        })
                    }

                    Thread(Runnable {
                        AppRoomDatabase.getInstance(mContext).listItemDao().insertAll(sList)
                    }).start()

                    isLoading.set(false)

                    CoroutineScope(Dispatchers.Main).launch {
                        mAdapter.get()?.addItems(list)
                    }
                }
            }

            override fun onError(error: ApiError) {
                isLoading.set(false)
            }

        })
    }

    private fun loadOffline(list: MutableList<ListItem>) {
        val viewModels = ArrayList<ViewModelItem>()
        for (item in list) {
            viewModels.add(ListItemViewModel(Item().apply {
                title = item.title ?: ""
                media?.m = item.link ?: ""
                link = item.link ?: ""
            }))
        }

        isLoading.set(false)

        CoroutineScope(Dispatchers.Main).launch {
            mAdapter.get()?.addItems(viewModels)
        }
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