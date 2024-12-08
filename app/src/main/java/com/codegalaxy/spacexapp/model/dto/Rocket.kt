package com.codegalaxy.spacexapp.model.dto

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("rocket_name") val rocketName: String
)
