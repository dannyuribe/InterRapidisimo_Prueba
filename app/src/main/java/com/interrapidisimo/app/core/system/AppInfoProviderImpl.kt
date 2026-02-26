package com.interrapidisimo.app.core.system

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppInfoProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AppInfoProvider{
    override fun getLocalVersion(): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "0.0.0"
        }catch (e: Exception){
            "0.0.0"
        }
    }
}