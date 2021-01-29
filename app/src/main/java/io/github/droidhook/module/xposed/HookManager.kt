package io.github.droidhook.module.xposed

import io.github.droidhook.module.hook.*

object HookManager {

    fun start(classLoader: ClassLoader) {
        SmsHook.start(classLoader)
        NetworkHook.start(classLoader)
        IntentHook.start(classLoader)
        TelephonyHook.start(classLoader)
        NotificationHook.start(classLoader)
        ContentResolverHook.start(classLoader)
    }

}