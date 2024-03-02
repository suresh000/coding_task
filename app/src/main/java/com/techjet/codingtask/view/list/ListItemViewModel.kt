package com.techjet.codingtask.view.list

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableField
import com.techjet.codingtask.AppNavigator
import com.techjet.codingtask.base.adapter.ViewModelItem
import com.techjet.codingtask.model.list.Item


class ListItemViewModel(private val mContext: Context, item: Item) : ViewModelItem {

    @JvmField
    val mItem = ObservableField(item)

    @JvmField
    val imageBitmap = ObservableField<Bitmap>()

    @JvmField
    val imageUri = ObservableField<Uri>()

    init {
        /*if (!NetworkUtil.isConnected(mContext)) {
            if (FileUtils.isExit(item.localImagePath)) {
                val uri = FileProvider.getUriForFile(mContext, FileUtils.FILE_PROVIDER, File(item.localImagePath))
                val file = File(item.localImagePath)
                imageUri.set(uri)
                //val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                //imageBitmap.set(bitmap)
            }
        }*/
    }

    fun itemClick(view: View) {
        val item = mItem.get()
        item?.let {
            if (!TextUtils.isEmpty(it.link)) {
                AppNavigator.openBrowser(view.context, it.link)
            }
        }
    }

}