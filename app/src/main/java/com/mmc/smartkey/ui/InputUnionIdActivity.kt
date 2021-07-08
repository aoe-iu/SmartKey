package com.mmc.smartkey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.mmc.smartkey.App
import com.mmc.smartkey.databinding.ActivityInputUnionIdBinding
import com.mmc.smartkey.R
import com.mmc.smartkey.network.KeyConfig

class InputUnionIdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputUnionIdBinding
    private val viewModel by viewModels<InputViewModel>()

    private  val tips = """使用PC版微信小程序抓包,输入encryptionUnionid
        request:
            /service/system/user/getUnionidByCode/v2/****

        response:
	        "data": {
		        "encryptionUnionid": "***",(使用这个参数)
		        "unionid": "***"(这个是未加密的，不能用)
	            }
    """

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
    }

    private fun initListener() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.confirmButton.setOnClickListener {
            val unionId = binding.TextInputEditText.text?.trim().toString()
            if (unionId.isNotEmpty()) {
                binding.confirmButton.isEnabled = false
                viewModel.getToken(unionId)
            }
        }
        viewModel.tokenLiveData.observe(this, {
            val result = it.getOrNull()
            if (result != null) {
                App.token = result.token
                App.houseId = result.houseHostId
                App.userId = result.peopleId
                App.unionId = binding.TextInputEditText.text?.trim().toString()
                KeyConfig.getInstance(applicationContext).updateUnionId(App.unionId)
                KeyConfig.getInstance(applicationContext).updateToken(result.token)
                KeyConfig.getInstance(applicationContext).updateUserID(result.peopleId)
                KeyConfig.getInstance(applicationContext).updateHouseID(result.houseHostId)
                Toast.makeText(this, "refresh token success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.unionId_error, Toast.LENGTH_SHORT).show()
                binding.TextInputEditText.requestFocus()
            }
            binding.confirmButton.isEnabled = true
        })
    }

    private fun initView() {
        binding = ActivityInputUnionIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_navigate_before_24)
        binding.TextInputEditText.setText(KeyConfig.getInstance(this).getUnionId())
        binding.tips.text = tips
    }
}