package io.github.droidhook.module.util

import android.os.Environment
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.regex.Pattern

object PackageNameUtil {
    private const val fileName = "PackageName"
    private var packageNameCache: String? = null

    fun getPackageName(): String? {
        return try {
            val file = File(Environment.getExternalStorageDirectory(), fileName)
            val fis = FileInputStream(file)
            val b = ByteArray(1024)
            var len: Int
            val baos = ByteArrayOutputStream()
            while (fis.read(b).apply { len = this } != -1) {
                baos.write(b, 0, len)
            }
            val data = baos.toByteArray()
            baos.close()
            fis.close()
            var packageName = String(data)
            packageName = replaceSpecialStr(packageName.trim { it <= ' ' })
            Log.d("PackageNameUtil", "PackageName = $packageName")
            packageNameCache = packageName
            packageName
        } catch (e: Exception) {
            //LogUtil.xposedLog("getPackageName() error!")
            //e.printStackTrace()
            // LogUtil.xposedLog(e.stackTraceToString())
            packageNameCache
        }
    }

//    fun getPackageName(): String {
//        val inputStream: FileInputStream = MonitorApplication.context.openFileInput(fileName)
//        val b = ByteArray(1024)
//        var stringBuilder = StringBuilder("")
//        var len = 0
//        while (inputStream.read(b).apply { len = this } > 0) {
//            stringBuilder.append(String(b, 0, len))
//        }
//        inputStream.close()
//        return stringBuilder.toString()
//    }

    private fun replaceSpecialStr(str: String?): String {
        var repl = ""
        if (str != null) {
            val p = Pattern.compile("\\s*|\t|\r|\n")
            val m = p.matcher(str)
            repl = m.replaceAll("")
        }
        return repl
    }

}