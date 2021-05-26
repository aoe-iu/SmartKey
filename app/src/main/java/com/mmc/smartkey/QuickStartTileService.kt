package com.mmc.smartkey


import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.mmc.smartkey.network.Repository

class QuickStartTileService : TileService() {
    companion object {
        const val TAG = "QuickStartTileService"
    }

    //当用户从Edit栏添加到快速设置中调用
    override fun onTileAdded() {
        super.onTileAdded()
    }

    //当用户从快速设置栏中移除的时候调用
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onClick() {
        super.onClick()
        Log.d(TAG, "onClick state = ${qsTile.state}")
        if (App.token.isEmpty()) {
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            qsTile.state = Tile.STATE_ACTIVE
            Repository.quickOpenDoor(Repository.getKeyParams())
        }
        qsTile.updateTile()
    }


    // 打开下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
    //在TleAdded之后会调用一次
    override fun onStartListening() {
        super.onStartListening()
    }

    // 关闭下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
    // 在onTileRemoved移除之前也会调用移除
    override fun onStopListening() {
        super.onStopListening()
    }
}