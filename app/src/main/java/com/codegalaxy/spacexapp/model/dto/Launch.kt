package com.codegalaxy.spacexapp.model.dto

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("launch_date_utc") val launchDate: String,
    @SerializedName("rocket") val rocket: Rocket,
    @SerializedName("launch_site") val launchSite: LaunchSite,
    @SerializedName("links") val links: LaunchLinks,
    @SerializedName("details") val details: String?
)
