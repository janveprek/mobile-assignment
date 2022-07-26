package com.veprek.honza.rocketlaunch.feature.list.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.veprek.honza.rocketlaunch.feature.list.presentation.RocketListViewModel
import com.veprek.honza.rocketlaunch.navigation.NavigationScreens
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.repository.model.State
import com.veprek.honza.rocketlaunch.ui.theme.RocketLaunchTheme
import org.koin.androidx.compose.viewModel

@Composable
fun RocketListScreen(
    navController: NavController
) {
    val viewModel: RocketListViewModel by viewModel()

    val rockets = viewModel.rockets.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()

    RocketListScreenImpl(
        rockets = rockets.value.data,
        state = rockets.value.state,
        isRefreshing = isRefreshing.value,
        refreshAction = { viewModel.refresh() },
        detailAction = { id -> navController.navigate(NavigationScreens.RocketDetailScreen.route + "/$id") }
    )
}

@Preview(showBackground = true)
@Composable
fun RocketListScreenPreview(
    modifier: Modifier = Modifier,
    rockets: List<Rocket> = listOf(),
    state: State = State.LOADING
) {
    RocketLaunchTheme {
//        RocketListScreen()
    }
}
