package io.github.droidhook.module.hook

import android.net.Uri
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import io.github.droidhook.module.model.LogMessage
import io.github.droidhook.module.util.FindMethodUtil
import io.github.droidhook.module.util.LogUtil


object ContentResolverHook : HookInterface {

    override fun start(classLoader: ClassLoader) {
        LogUtil.log("ContentResolverHook", "start()")
        XposedBridge.hookMethod(
            FindMethodUtil.findMethod(
                "android.content.ContentResolver",
                classLoader,
                "query",
                Uri::class.java,
                Array<String>::class.java,
                String::class.java,
                Array<String>::class.java,
                String::class.java
            ),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    var uri = param.args[0] as Uri
                    val msgArray = mapOf(
                        "uri" to uri.toString()
                    )
                    LogUtil.xposedLog(LogMessage("ContentResolver", "query", msgArray.toString()))
                }
            }
        )
    }

}