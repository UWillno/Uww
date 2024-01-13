package com.uwillno.uww

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class MyQSTileService:TileService() {
    private val sharedPrefName = "mySharedPreferences"
    private val editTextKey = "dirs"
    private lateinit var dirs :List<String>
    private fun loadTextFromSharedPreferences() {
        // 获取SharedPreferences对象
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

        // 从SharedPreferences中获取之前保存的文本
        val storedText = sharedPreferences.getString(editTextKey, "")
        if (storedText != null) {
            dirs =storedText.split(";").distinct()
        };

    }


    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onStopListening() {
        super.onStopListening()
    }

    override fun onClick() {
        super.onClick()
        val state = qsTile.state

        when (state) {
            Tile.STATE_INACTIVE -> {
                loadTextFromSharedPreferences()
                qsTile.state = Tile.STATE_ACTIVE// 更改成活跃状态
                qsTile.label = "隐藏中"
                dirs.forEach{
                    dotDir(it);
                }
            }
            Tile.STATE_ACTIVE -> {
                loadTextFromSharedPreferences()
                qsTile.state = Tile.STATE_INACTIVE//更改成非活跃状态
                qsTile.label = "可见中"
                dirs.forEach{
                noDotDir(it)
                }
            }
            Tile.STATE_UNAVAILABLE -> {

            }
        }
        qsTile.updateTile()//更新Tile

    }
}