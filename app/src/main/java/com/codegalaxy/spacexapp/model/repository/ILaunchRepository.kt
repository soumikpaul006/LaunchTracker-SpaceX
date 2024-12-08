package com.codegalaxy.spacexapp.model.repository

import com.codegalaxy.spacexapp.model.dto.Launch
import kotlinx.coroutines.flow.Flow

interface ILaunchRepository {
    suspend fun getAllLaunches(): Flow<List<Launch>>
}