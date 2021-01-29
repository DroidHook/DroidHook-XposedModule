package io.github.droidhook.module.hook

import android.app.PendingIntent
import android.util.Base64
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil

object SmsHook: HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("SmsHook", "start()")
        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "android.telephony.SmsManager",
                classLoader,
                "sendTextMessage",
                String::class.java,
                String::class.java,
                String::class.java,
                PendingIntent::class.java,
                PendingIntent::class.java
            ),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val smsDestinationAddress = param.args[0]
                    val smsText = param.args[2]
                    val msgArray = mapOf(
                        "destinationAddress" to smsDestinationAddress as String,
                        "text" to smsText as String
                    )
                    LogUtil.xposedLog(LogMessage("Sms", "sendTextMessage", msgArray.toString()))
                }
            })

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "android.telephony.SmsManager",
                classLoader,
                "sendDataMessage",
                String::class.java,
                String::class.java,
                Short::class.java,
                ByteArray::class.java,
                PendingIntent::class.java,
                PendingIntent::class.java
            ),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val smsDestinationAddress = param.args[0]
                    val smsPort = param.args[2]
                    val smsData = Base64.encodeToString(param.args[3] as ByteArray, 0)
                    val msgArray = mapOf(
                        "destinationAddress" to smsDestinationAddress as String,
                        "port" to smsPort.toString(),
                        "data" to smsData as String
                    )
                    LogUtil.xposedLog(LogMessage("Sms", "sendDataMessage", msgArray.toString()))
                }
            })
    }

}