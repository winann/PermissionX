package com.winann.permissionx

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

typealias PermissionsResultCallBack =  (Boolean, Array<String>) -> Unit
internal class InvisibleFragment: Fragment() {

    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val rejectedPermissions = it.filter { result -> !result.value }.map { (key, value) ->  key }.toTypedArray()
        permissionsResultCallBack?.invoke(rejectedPermissions.isEmpty(), rejectedPermissions)
    }
    private var permissionsResultCallBack: PermissionsResultCallBack? = null

    fun request(permissions: Array<String>, callBack: PermissionsResultCallBack) {
        permissionsResultCallBack = callBack

        val needRequestPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()
        if (needRequestPermissions.isEmpty()) {
            permissionsResultCallBack?.invoke(true, arrayOf())
        } else {
            requestPermissionsLauncher.launch(needRequestPermissions)
        }

    }

}