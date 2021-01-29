package io.github.droidhook.module.hook

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil
import java.net.URL

object NetworkHook : HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("NetworkHook", "start()")
        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("java.net.URL", classLoader, "openConnection"),
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val url = param.thisObject as URL
                    val msgArray = mapOf(
                        "url" to url.toString()
                    )
                    LogUtil.xposedLog(LogMessage("Network", "openConnection", msgArray.toString()))
                    super.beforeHookedMethod(param)
                }
            })
    }

}