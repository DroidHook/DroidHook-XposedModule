package io.github.droidhook.module.util

import android.util.Log
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage

object LogUtil {

    fun xposedLog(message: LogMessage) {
        XposedBridge.log("KotlinXposedMonitor - ${message.hook}: ${message.method} - ${message.msg}" )
    }

    fun log(tag: String, text: String) {
        Log.d(tag, text)
    }

}