package com.veprek.honza.rocketlaunch.feature.list.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.veprek.honza.rocketlaunch.repository.model.Rocket

@Composable
fun RocketList(modifier: Modifier = Modifier, rockets: List<Rocket>, navController: NavController) {
    ShowRocketsList(modifier = modifier, rockets = rockets, navController = navController)
}
