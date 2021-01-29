package io.github.droidhook.module.util

import java.lang.reflect.Method

object FindMethodUtil {
    fun findMethod(
        className: String,
        classLoader: ClassLoader,
        methodName: String,
        vararg parameterTypes: Class<*>?
    ): Method? {
        try {
            val clazz = classLoader.loadClass(className)
            val method = clazz.getDeclaredMethod(methodName, *parameterTypes)
            method.isAccessible = true
            return method
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}