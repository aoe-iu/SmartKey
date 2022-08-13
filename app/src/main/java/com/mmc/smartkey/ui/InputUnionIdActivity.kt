package com.mmc.smartkey.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.mmc.smartkey.App
import com.mmc.smartkey.R
import com.mmc.smartkey.databinding.ActivityInputUnionIdBinding
import com.mmc.smartkey.network.KeyConfig
import com.mmc.smartkey.network.model.RefreshListResult

class InputUnionIdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputUnionIdBinding
    private val viewModel by viewModels<InputViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    private fun initListener() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.confirmButton.setOnClickListener {
            val unionId = binding.textInputEditText.text?.trim().toString()
            if (unionId.isNotEmpty()) {
                binding.confirmButton.isEnabled = false
                binding.loadingPb.show()
                viewModel.getToken(unionId)
            }
        }
        viewModel.tokenLiveData.observe(this) {
            val result = it.getOrNull()
            if (result != null) {
                showHouseListDialog(result)
            } else {
                Toast.makeText(this, R.string.unionId_error, Toast.LENGTH_SHORT).show()
                binding.textInputEditText.requestFocus()
            }
            binding.confirmButton.isEnabled = true
            binding.loadingPb.hide()
        }
    }

    private fun initView() {
        binding = ActivityInputUnionIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_navigate_before_24)
        binding.textInputEditText.setText(KeyConfig.getInstance(this).getUnionId())
        binding.loadingPb.hide()
    }

    @SuppressLint("CheckResult")
    private fun showHouseListDialog(refreshListResult: RefreshListResult) {
        val addressStrList = refreshListResult.dataResult.map { it.hostAddress }
        MaterialDialog(this).show {
            title(R.string.choose_address)
            listItemsSingleChoice(items = addressStrList) { _, index, text ->
                App.unionId = binding.textInputEditText.text?.trim().toString()
                App.token = refreshListResult.token
                App.userId = refreshListResult.peopleId
                App.houseId = refreshListResult.dataResult[index].houseHostId
                KeyConfig.getInstance(applicationContext).updateUnionId(App.unionId)
                KeyConfig.getInstance(applicationContext).updateToken(App.token)
                KeyConfig.getInstance(applicationContext).updateUserID(App.userId)
                KeyConfig.getInstance(applicationContext).updateHouseID(App.houseId)
            }
        }
    }
}