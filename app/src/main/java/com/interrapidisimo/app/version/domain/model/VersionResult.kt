package com.interrapidisimo.app.version.domain.model

sealed class VersionResult {
    data class Inferior(val local: String, val remote: String) : VersionResult()
    data class Superior(val local: String, val remote: String) : VersionResult()
    data class Update(val local: String) : VersionResult()
}