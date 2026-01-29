package com.interrapidisimo.app.version.data.repository

import com.interrapidisimo.app.version.data.api.VersionService

class VersionRepository(
    private val service: VersionService
) {
    suspend fun getRemoteVersion(): Result<String>{
        return  try {
            val response = service.getVersion()
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Error versión: ${response.code()}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}