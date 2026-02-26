package com.interrapidisimo.app.version.domain

import com.interrapidisimo.app.core.system.AppInfoProvider
import com.interrapidisimo.app.version.data.repository.VersionRepository
import com.interrapidisimo.app.version.domain.model.VersionResult
import javax.inject.Inject

class CheckVersionUseCase @Inject constructor(
    private val repository: VersionRepository,
    private val appInfoProvider: AppInfoProvider
){
    suspend operator fun invoke(): Result<VersionResult>{
        val localVersion = appInfoProvider.getLocalVersion()
        return try {
            val remoteResult = repository.getRemoteVersion()

            remoteResult.map { remoteVersion ->
            when{
                compareVersions(localVersion, remoteVersion) < 0 ->
                    VersionResult.Inferior(localVersion, remoteVersion)

                compareVersions(localVersion, remoteVersion) > 0 ->
                    VersionResult.Superior(localVersion, remoteVersion)
                else ->
                    VersionResult.Update(localVersion)
            }}

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
}