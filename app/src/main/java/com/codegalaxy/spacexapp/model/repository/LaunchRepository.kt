package com.codegalaxy.spacexapp.model.repository

import com.codegalaxy.spacexapp.model.remote.SpaceXApiService
import com.codegalaxy.spacexapp.model.dto.Launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val apiService: SpaceXApiService
) : ILaunchRepository {
    override suspend fun getAllLaunches(): Flow<List<Launch>> = flow {
        try {
            val launches = apiService.getAllLaunches()
            emit(launches.sortedByDescending { it.launchDate })
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}