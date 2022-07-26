package com.veprek.honza.rocketlaunch.feature.detail.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.veprek.honza.rocketlaunch.feature.detail.presentation.RocketDetailViewModel
import com.veprek.honza.rocketlaunch.navigation.NavigationScreens
import quanti.com.kotlinlog.Log

@Composable
fun RocketDetailScreen(
    navController: NavController,
    id: String?
) {
    val viewModel: RocketDetailViewModel = hiltViewModel()

    Log.d("Created Screen")

    LaunchedEffect(Unit) {
        id?.let {
            Log.d("Rocket id: $id")
            viewModel.getRocket(id)
        }
    }

    val rocketFlow = viewModel.rocket.collectAsState()
    val context = LocalContext.current

    RocketDetailScreenImpl(
        rocketState = rocketFlow.value.state,
        rocketData = rocketFlow.value.data,
        backAction = { navController.popBackStack() },
        imageAction = { imageId ->
            viewModel.getFile(imageId, context)
        },
        launchAction = { navController.navigate(NavigationScreens.RocketLaunchScreen.route) }
    )
}
