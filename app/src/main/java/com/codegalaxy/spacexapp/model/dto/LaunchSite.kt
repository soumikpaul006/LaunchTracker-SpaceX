package com.codegalaxy.spacexapp.model.dto

import com.google.gson.annotations.SerializedName

data class LaunchSite(
    @SerializedName("site_name_long") val siteName: String
)