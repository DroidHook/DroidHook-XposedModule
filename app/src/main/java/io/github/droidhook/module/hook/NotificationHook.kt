package io.github.droidhook.module.hook

import android.app.Notification
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil

object NotificationHook : HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("NotificationHook", "start()")
        XposedBridge.hookMethod(
                FindMethodUtil.findMethod(
                    "androidx.core.app.NotificationManagerCompat",
                    classLoader,
                    "notify",
                    String::class.java,
                    Int::class.java,
                    Notification::class.java
                ),
        object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                super.beforeHookedMethod(param)
                val tag = param.args[0]
                val id = param.args[1]
                val msgArray = mapOf(
                    "tag" to tag as String,
                    "id" to id as Int
                )
                LogUtil.xposedLog(LogMessage("Notification", "notify", msgArray.toString()))
            }
        })

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "androidx.core.app.NotificationManagerCompat",
                classLoader,
                "notify",
                Int::class.java,
                Notification::class.java
            ),
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val id = param.args[0]
                    val msgArray = mapOf(
                        "id" to id as Int
                    )
                    LogUtil.xposedLog(LogMessage("Notification", "notify", msgArray.toString()))
                }
            })

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "android.app.NotificationManager",
                classLoader,
                "notify",
                String::class.java,
                Int::class.java,
                Notification::class.java
            ),
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val tag = param.args[0]
                    val id = param.args[1]
                    val msgArray = mapOf(
                        "tag" to tag as String,
                        "id" to id as Int
                    )
                    LogUtil.xposedLog(LogMessage("Notification", "notify", msgArray.toString()))
                }
            })

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "android.app.NotificationManager",
                classLoader,
                "notify",
                Int::class.java,
                Notification::class.java
            ),
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val id = param.args[0]
                    val msgArray = mapOf(
                        "id" to id as Int
                    )
                    LogUtil.xposedLog(LogMessage("Notification", "notify", msgArray.toString()))
                }
            })
    }

}