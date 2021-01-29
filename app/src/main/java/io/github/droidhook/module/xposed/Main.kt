package io.github.droidhook.module.xposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.github.droidhook.module.util.PackageNameUtil


class Main: IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        val packageName: String = PackageNameUtil.getPackageName().toString()

        if (lpparam.packageName == packageName) {
                // LogUtil.xposedLog(LogMessage(packageName, "HOOK START!"))
                HookManager.start(lpparam.classLoader)
            }
    }

}