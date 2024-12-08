package com.codegalaxy.spacexapp.model.dto

import com.google.gson.annotations.SerializedName

data class LaunchLinks(
    @SerializedName("mission_patch_small") val patchImage: String?,
    @SerializedName("wikipedia") val wikipediaLink: String?
)
