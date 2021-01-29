package io.github.droidhook.module.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.github.droidhook.module.R
import io.github.droidhook.module.util.LogUtil
import io.github.droidhook.module.util.PackageNameUtil
import kotlinx.android.synthetic.main.activity_main_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

        verifyReadStoragePermission(this)

        currentAppNameTextView.text = PackageNameUtil.getPackageName()

        updateBtn.setOnClickListener {
            currentAppNameTextView.text = PackageNameUtil.getPackageName()
        }

        saveBtn.setOnClickListener {
            val name = PackageNameUtil.getPackageName()
            getSharedPreferences(packageName + "_preferences", Activity.MODE_PRIVATE).edit().apply {
                clear()
                putString("packageName", name)
                apply()
            }
            Toast.makeText(this, "$name saved.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyReadStoragePermission(activity: Activity) {
        try {
            val permission = ActivityCompat.checkSelfPermission(
                activity,
                "android.permission.READ_EXTERNAL_STORAGE"
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtil.log("MainActivity", "verifyReadStoragePermission() error!")
        }
    }
}