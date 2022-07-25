package com.veprek.honza.rocketlaunch.feature.launch.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.veprek.honza.rocketlaunch.feature.launch.presentation.RocketLaunchViewModel
import com.veprek.honza.rocketlaunch.navigation.NavigationScreens
import quanti.com.kotlinlog.Log
import kotlin.math.abs

@Composable
fun RocketLaunchScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: RocketLaunchViewModel = hiltViewModel()

    val context = LocalContext.current
    val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    var gravity: FloatArray? = null
    var geomagnetic: FloatArray? = null

    val launched = viewModel.isLaunched.collectAsState()
    val state = viewModel.rocketState.collectAsState().value

    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_GRAVITY) {
                gravity = event.values
            }

            if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
                geomagnetic = event.values
            }

            if (gravity != null && geomagnetic != null) {
                val rotationMatrix = FloatArray(9)
                val inclinationMatrix = FloatArray(9)

                if (SensorManager.getRotationMatrix(
                        rotationMatrix,
                        inclinationMatrix,
                        gravity,
                        geomagnetic
                    )
                ) {
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, orientation)
                    val azimuth = Math.toDegrees(orientation[0].toDouble())
                    val pitch = Math.toDegrees(orientation[1].toDouble())
                    val roll = Math.toDegrees(orientation[2].toDouble())
                    Log.d("Orientation: $azimuth, $pitch, $roll")

                    if (abs(pitch + 10) >= 20) {
                        if (pitch < 0) viewModel.launch() else viewModel.fail()
                        Log.d("$this")
                        sensorManager.unregisterListener(this)
                    }
                }
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }

    LaunchedEffect(Unit) {
        sensorManager.registerListener(
            sensorEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            sensorEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    RocketLaunchScreenImpl(state = state, launched = launched.value, backAction = {
        navController.popBackStack(
            NavigationScreens.RocketDetailScreen.route,
            inclusive = false
        )
    })
}