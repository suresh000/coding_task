package com.techjet.codingtask.utils.bindingUtils

import android.text.Html
import android.text.Spannable
import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.techjet.codingtask.utils.handler.PicassoImageGetter


object TextViewBindingUtils {

    private const val SET_HTML_TEXT = "setHtmlText"

    @JvmStatic
    @BindingAdapter(SET_HTML_TEXT)
    fun setHtmlText(textView: TextView, htmlText: String?) {
        if (!TextUtils.isEmpty(htmlText)) {

            val imageGetter = PicassoImageGetter(textView)
            val html: Spannable = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY,
                imageGetter, null) as Spannable
            textView.text = html


            //textView.text = HtmlCompat.fromHtml(htmlText!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

}
