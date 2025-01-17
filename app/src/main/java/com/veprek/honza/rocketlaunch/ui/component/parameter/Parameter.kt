package com.veprek.honza.rocketlaunch.ui.component.parameter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.veprek.honza.rocketlaunch.ui.theme.RocketLaunchTheme
import com.veprek.honza.rocketlaunch.ui.theme.cornerRadius
import com.veprek.honza.rocketlaunch.ui.theme.pink
import com.veprek.honza.rocketlaunch.ui.theme.surfaceParameter

@Composable
fun Parameter(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Surface(
        modifier = modifier.size(surfaceParameter),
        shape = RoundedCornerShape(cornerRadius),
        color = pink,
        contentColor = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, style = MaterialTheme.typography.h1)
            Text(subtitle, style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParameterPreview(
    title: String = "90m",
    subtitle: String = "height"
) {
    RocketLaunchTheme {
        Parameter(title = title, subtitle = subtitle)
    }
}
