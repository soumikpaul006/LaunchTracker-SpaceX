import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.codegalaxy.spacexapp.model.dto.Launch
import com.codegalaxy.spacexapp.viewmodel.LaunchViewModel



enum class DeviceSizeClass {
    Small,
    Large
}

@Composable
fun calculateDeviceSizeClass(): DeviceSizeClass {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    return if (screenWidthDp < 850) DeviceSizeClass.Small else DeviceSizeClass.Large
}

@Composable
fun SpaceXLaunchesScreen(
    deviceSizeClass: DeviceSizeClass,
    viewModel: LaunchViewModel = hiltViewModel()
) {
    val launches by viewModel.launches.collectAsStateWithLifecycle()
    val selectedLaunch by viewModel.selectedLaunch.collectAsStateWithLifecycle()

    // Determine the orientation of the device
    val orientation = LocalConfiguration.current.orientation


    if (selectedLaunch != null) {
        BackHandler {
            viewModel.selectLaunch(null)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (deviceSizeClass) {
            DeviceSizeClass.Small -> {
                // Mobile devices: Sequential navigation
                if (selectedLaunch != null) {
                    LaunchDetailsView(
                        modifier = Modifier.fillMaxSize(),
                        launch = selectedLaunch!!
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(launches) { launch ->
                            LaunchListItem(
                                launch = launch,
                                isSelected = false,
                                onLaunchSelected = { viewModel.selectLaunch(it) }
                            )
                        }
                    }
                }
            }

            DeviceSizeClass.Large -> {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    // Large devices in portrait: Sequential navigation
                    if (selectedLaunch != null) {
                        LaunchDetailsView(
                            modifier = Modifier.fillMaxSize(),
                            launch = selectedLaunch!!
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(launches) { launch ->
                                LaunchListItem(
                                    launch = launch,
                                    isSelected = false,
                                    onLaunchSelected = { viewModel.selectLaunch(it) }
                                )
                            }
                        }
                    }
                } else {
                    // Large devices in landscape: Master/Detail split view
                    Row(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(0.4f)
                                .fillMaxHeight()
                        ) {
                            items(launches) { launch ->
                                LaunchListItem(
                                    launch = launch,
                                    isSelected = launch == selectedLaunch,
                                    onLaunchSelected = { viewModel.selectLaunch(it) }
                                )
                            }
                        }

                        selectedLaunch?.let { launch ->
                            LaunchDetailsView(
                                modifier = Modifier.weight(0.6f),
                                launch = launch
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchListItem(
    launch: Launch,
    isSelected: Boolean,
    onLaunchSelected: (Launch) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLaunchSelected(launch) }
            .padding(8.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.surface
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = launch.links.patchImage ?: "",
                contentDescription = launch.missionName,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = launch.missionName, style = MaterialTheme.typography.titleMedium)
                Text(text = launch.rocket.rocketName, style = MaterialTheme.typography.bodyMedium)
                Text(text = launch.launchSite.siteName, style = MaterialTheme.typography.bodySmall)
                Text(text = launch.launchDate, style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Composable
fun LaunchDetailsView(
    modifier: Modifier = Modifier,
    launch: Launch
) {
    Surface(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                model = launch.links.patchImage ?: "",
                contentDescription = launch.missionName,

                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Mission Name", style = MaterialTheme.typography.titleLarge)
            Text(text = launch.missionName, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Rocket", style = MaterialTheme.typography.titleLarge)
            Text(text = launch.rocket.rocketName, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Launch Site", style = MaterialTheme.typography.titleLarge)
            Text(text = launch.launchSite.siteName, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Launch Date", style = MaterialTheme.typography.titleLarge)
            Text(text = launch.launchDate, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            launch.details?.let { details ->
                Text(text = "Mission Details", style = MaterialTheme.typography.titleLarge)
                Text(text = details, style = MaterialTheme.typography.bodyLarge)
            }

        }
    }
}