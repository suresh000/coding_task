package com.techjet.codingtask.common.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.techjet.codingtask.common.internet.InternetLostDialog
import com.techjet.codingtask.utils.NetworkUtil

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (!NetworkUtil.isOnline(context)) {
            InternetLostDialog.show()
        } else {
            InternetLostDialog.hide()
        }

    }
}