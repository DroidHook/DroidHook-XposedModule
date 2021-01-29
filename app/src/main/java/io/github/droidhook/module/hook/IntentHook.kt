package io.github.droidhook.module.hook

import android.net.Uri
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil

object IntentHook : HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("IntentHook", "start()")
        XposedBridge.hookMethod(
            FindMethodUtil.findMethod("android.content.Intent", classLoader, "setDataAndType",
                Uri::class.java,
                String::class.java
            ),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    var data = param.args[0] as Uri
                    var type = param.args[1] as String
                    val msgArray = mapOf(
                        "data" to data.toString(),
                        "type" to type
                    )
                    LogUtil.xposedLog(LogMessage("Intent", "setDataAndType", msgArray.toString()))
                }
            }
        )
    }

}