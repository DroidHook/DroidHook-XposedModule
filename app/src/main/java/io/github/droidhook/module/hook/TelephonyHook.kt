package io.github.droidhook.module.hook

import android.telephony.PhoneStateListener
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil

object TelephonyHook : HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("TelephonyHook", "start()")
        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("android.telephony.TelephonyManager", classLoader, "getLine1Number"),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    LogUtil.xposedLog(LogMessage("TelephonyManager", "getLine1Number"))
                }
            }
        )

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("android.telephony.TelephonyManager", classLoader, "getDeviceId"),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    LogUtil.xposedLog(LogMessage("TelephonyManager", "getDeviceId"))
                }
            }
        )

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("android.telephony.TelephonyManager", classLoader, "getImei"),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    LogUtil.xposedLog(LogMessage("TelephonyManager", "getImei"))
                }
            }
        )

        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("android.telephony.TelephonyManager", classLoader, "listen",
                PhoneStateListener::class.java,
                Int::class.java
            ),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val events = param.args[1]
                    val msgArray = mapOf(
                        "events" to events.toString(),
                    )
                    LogUtil.xposedLog(LogMessage("TelephonyManager", "listen", msgArray.toString()))
                }
            }
        )
    }

}