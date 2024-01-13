package com.uwillno.uww

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions


class MainActivity : AppCompatActivity() {
    private val launcherActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        val data = it.data?.data?.path
        editText.append("/sdcard/"+data.toString().split(':')[1]+";")

    }


private lateinit var editText: EditText

    private val sharedPrefName = "mySharedPreferences"
    private val editTextKey = "dirs"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editTextTextMultiLine2)
       val button =  findViewById<Button>(R.id.button)
        loadTextFromSharedPreferences()
        button.setOnClickListener {
            saveTextToSharedPreferences()
            Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show()
        }

        XXPermissions.with(this)
            // 申请单个权限
            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            .request { permissions, allGranted ->
                Toast.makeText(
                    this@MainActivity,
                    "获取权限成功",
                    Toast.LENGTH_SHORT
                ).show()
            }

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        button.setOnLongClickListener {
            launcherActivity.launch(intent)
             true
        }



    }
    private fun saveTextToSharedPreferences() {
        val textToSave = editText.text.toString()

        // 获取SharedPreferences对象
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

        // 使用SharedPreferences编辑器存储数据
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(editTextKey, textToSave)
        editor.apply()
    }

    private fun loadTextFromSharedPreferences() {
        // 获取SharedPreferences对象
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

        // 从SharedPreferences中获取之前保存的文本
        val dirs = sharedPreferences.getString(editTextKey, "")

//        // 将文本设置到EditText中
//        if(dirs !="")
        editText.setText(dirs)
    }

}