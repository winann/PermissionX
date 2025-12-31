package com.winann.permissionx

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import java.security.Permissions

object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity, permissions: Array<String>, callBack: PermissionsResultCallBack) {
        val supportManager = activity.supportFragmentManager
        val fragment: InvisibleFragment
        val existedFragment = supportManager.findFragmentByTag(TAG)
        if (null != existedFragment) {
            fragment = existedFragment as InvisibleFragment
        } else {
            fragment = InvisibleFragment()
            supportManager.beginTransaction().add(fragment, TAG).commitNow()
        }
        fragment.request(permissions, callBack)
    }
}