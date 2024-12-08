package com.codegalaxy.spacexapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codegalaxy.spacexapp.model.dto.Launch
import com.codegalaxy.spacexapp.model.repository.ILaunchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val repository: ILaunchRepository
) : ViewModel() {

    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>> = _launches.asStateFlow()

    private val _selectedLaunch = MutableStateFlow<Launch?>(null)
    val selectedLaunch: StateFlow<Launch?> = _selectedLaunch.asStateFlow()

    init {
        fetchLaunches()
    }

    private fun fetchLaunches() {
        viewModelScope.launch {
            repository.getAllLaunches().collect { launchesList ->
                _launches.value = launchesList
            }
        }
    }

    fun selectLaunch(launch: Launch?) {
        _selectedLaunch.value = launch
    }
}