package com.codegalaxy.spacexapp.model.remote

import com.codegalaxy.spacexapp.model.dto.Launch
import retrofit2.http.GET

interface SpaceXApiService {
    @GET("launches")
    suspend fun getAllLaunches(): List<Launch>
}