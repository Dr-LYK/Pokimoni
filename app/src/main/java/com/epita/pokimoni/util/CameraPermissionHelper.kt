package com.epita.pokimoni.util

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object CameraPermissionHelper {
    private val CAMERA_PERMISSION_CODE = 0
    private val CAMERA_PERMISSION = Manifest.permission.CAMERA

    fun hasCameraPermission(activity: Activity): Boolean {
        return ContextCompat.checkSelfPermission(activity, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(CAMERA_PERMISSION), CAMERA_PERMISSION_CODE
        )
    }

    fun shouldShowRequestPermissionRationale(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, CAMERA_PERMISSION)
    }

    fun launchPermissionSettings(activity: Activity) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", activity.packageName, null)
        activity.startActivity(intent)
    }
}