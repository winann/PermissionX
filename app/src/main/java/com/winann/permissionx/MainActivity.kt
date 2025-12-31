package com.winann.permissionx

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dialButton = findViewById<Button>(R.id.dial_button)
        dialButton.setOnClickListener {
            PermissionX.request(this, arrayOf(Manifest.permission.CALL_PHONE)) { isAllGranted, rejectedList ->
                if (isAllGranted) {
                    call()
                } else {
                    Toast.makeText(this, "未授权", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "rejected permissions: $rejectedList")
                }
            }
        }

    }

    private fun call() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.setData("tel:10000".toUri())
        startActivity(intent)
    }
    companion object {
        private const val TAG = "MainActivity"
    }
}