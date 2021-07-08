
package com.mmc.smartkey.network

import android.content.Context
import androidx.core.content.edit

private const val USER_PREFERENCES_NAME = "user_preferences"
private const val KEY_TOKEN = "key_token"
private const val KEY_UNIONID = "key_unionId"
private const val KEY_USER_ID = "key_user_id"
private const val KEY_HOUSE_ID = "key_house_id"
private const val KEY_AUTO_OPEN = "key_auto_open"
private const val KEY_HOUSE_ADDRESS = "key_house_address"


/**
 * Class that handles saving and retrieving user preferences
 */
class KeyConfig private constructor(context: Context) {

    private val sharedPreferences =
        context.applicationContext.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun updateToken(token: String) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token)
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun updateUnionId(unionId: String) {
        sharedPreferences.edit {
            putString(KEY_UNIONID, unionId)
        }
    }

    fun getUnionId(): String? {
        return sharedPreferences.getString(KEY_UNIONID, null)
    }


    fun updateUserID(userID: String) {
        sharedPreferences.edit {
            putString(KEY_USER_ID, userID)
        }
    }

    fun getUserID(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun updateHouseID(houseID: String) {
        sharedPreferences.edit {
            putString(KEY_HOUSE_ID, houseID)
        }
    }

    fun getHouseAddress(): String? {
        return sharedPreferences.getString(KEY_HOUSE_ADDRESS, null)
    }

    fun updateHouseAddress(houseAddress: String) {
        sharedPreferences.edit {
            putString(KEY_HOUSE_ADDRESS, houseAddress)
        }
    }

    fun getHouseID(): String? {
        return sharedPreferences.getString(KEY_HOUSE_ID, null)
    }

    fun updateAutoOpen(isAuto: Boolean) {
        sharedPreferences.edit {
            putBoolean(KEY_AUTO_OPEN, isAuto)
        }
    }

    fun isAutoOpen(): Boolean {
        return sharedPreferences.getBoolean(KEY_AUTO_OPEN, false)
    }

    companion object {
        @Volatile
        private var INSTANCE: KeyConfig? = null

        fun getInstance(context: Context): KeyConfig {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = KeyConfig(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
