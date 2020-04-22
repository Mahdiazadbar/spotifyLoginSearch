package com.azadbar.digipaychallenge.utility

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

open class AppSharedPreferences  @Inject constructor(application: Application) {
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("shared preferences", 0)

    open fun setAuthToken(authToken: String?) {
            sharedPreferences.edit()
                .putString("token", "Bearer $authToken")
                .apply()
    }

    fun getAuthToken(): String {
        return sharedPreferences.getString("token", "")!!
    }

    fun removeAuthToken() {
        sharedPreferences.edit()
                .remove("token")
                .apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("token", "") != ""
    }
}