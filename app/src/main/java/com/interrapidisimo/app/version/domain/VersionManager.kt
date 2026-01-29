package com.interrapidisimo.app.version.domain

import android.content.Context
import com.interrapidisimo.app.version.data.repository.VersionRepository

class VersionManager(
    private val repository: VersionRepository
){
    suspend fun checkVersion(context: Context): Result<String> {

        var localVersion = getLocalVersion(context)

        return try {
            val remoteResult = repository.getRemoteVersion()

            remoteResult.map { remote ->
                val remoteVersion = remote

                when {
                    compareVersions(localVersion, remoteVersion) < 0 ->
                        "Tu versión es INFERIOR a la requerida ($remoteVersion)"
                    compareVersions(localVersion, remoteVersion) > 0 ->
                        "Tu versión es SUPERIOR a la versión del servidor ($remoteVersion)"
                    else -> "Tu aplicación está ACTUALIZADA"
                }
            }

        }catch (e: Exception){
            Result.failure(e)
        }
    }

    private fun compareVersions(v1: String, v2: String): Int {
        val parts1 = v1.split(".")
        val parts2 = v2.split(".")

        for (i in 0 until maxOf(parts1.size, parts2.size)) {
            val num1 = parts1.getOrNull(i)?.toIntOrNull() ?: 0
            val num2 = parts2.getOrNull(i)?.toIntOrNull() ?: 0

            if (num1 != num2) {
                return num1 - num2
            }
        }
        return 0
    }

    private fun  getLocalVersion(context: Context): String{
        try {
            val pInfo = context.packageManager
                .getPackageInfo(context.packageName, 0)
            return pInfo.versionName ?: "0.0.0"
        }catch (e: Exception){
            return "0.0.0"
        }
    }
}