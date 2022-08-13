package com.mmc.smartkey.ui


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mmc.smartkey.App
import com.mmc.smartkey.R
import com.mmc.smartkey.databinding.ActivityMainBinding
import com.mmc.smartkey.network.KeyConfig
import com.mmc.smartkey.network.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //    val viewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initListener()
        initObserve()
    }

    override fun onResume() {
        super.onResume()
        if (App.token.isEmpty()) {
//            refreshToken()
            Toast.makeText(this, "please input unionID", Toast.LENGTH_SHORT).show()
        } else {
            if (KeyConfig.getInstance(applicationContext)
                    .isAutoOpen()
            ) openDoor() else binding.loadingPb.hide()
        }
    }

    private fun initListener() {
        binding.openDoorFab.setOnClickListener { openDoor() }
//        binding.qrCodeFab.setOnClickListener {
//            binding.loadingPb.show()
//            viewModel.searchQRData(Repository.getQRCodeParams())
//        }
    }

    private fun openDoor() {
        if (App.token.isEmpty()) {
            Toast.makeText(this, "please input unionID", Toast.LENGTH_SHORT).show()
            return
        }
        binding.loadingPb.show()
        viewModel.openDoor(Repository.getKeyParams())
    }


    private fun initObserve() {
/*        viewModel.tokenLiveData.observe(this) {
            val result = it.getOrNull()
            if (result != null) {
                App.token = result.token
                App.houseId = result.houseHostId
                App.userId = result.peopleId
                KeyConfig.getInstance(applicationContext).updateToken(result.token)
                KeyConfig.getInstance(applicationContext).updateUserID(result.peopleId)
                KeyConfig.getInstance(applicationContext).updateHouseID(result.houseHostId)
                Toast.makeText(this, "refresh token success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            binding.loadingPb.hide()
        }*/
        viewModel.openDoorLiveData.observe(this) {
            val msg = it.getOrNull()
            Toast.makeText(this, msg ?: "error", Toast.LENGTH_SHORT).show()
            binding.loadingPb.hide()
        }
        /* viewModel.qrLiveData.observe(this, {
             val qrCodeStr = it.getOrNull()
             if (qrCodeStr != null) {
                 val qrCodeBitmap = MakeQRCode.createQRCodeBitmap(
                     qrCodeStr,
                     binding.qrCodeIV.width,
                     binding.qrCodeIV.width,
                     "UTF-8",
                     "H",
                     "1",
                     Color.BLACK,
                     Color.WHITE
                 )
                 binding.qrCodeIV.setImageBitmap(qrCodeBitmap)
             } else {
                 Toast.makeText(this, getString(R.string.qr_code_error), Toast.LENGTH_SHORT).show()
                 it.exceptionOrNull()?.printStackTrace()
             }
             binding.loadingPb.hide()
         })*/
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.autoOpen).isChecked =
            KeyConfig.getInstance(applicationContext).isAutoOpen()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.inputUnionId -> {
                startActivity(Intent(this, InputUnionIdActivity::class.java))
                true
            }
            R.id.autoOpen -> {
                item.isChecked = !item.isChecked
                KeyConfig.getInstance(applicationContext)
                    .updateAutoOpen(item.isChecked)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshToken() {
        binding.loadingPb.show()
        viewModel.getToken(App.unionId)
    }

}