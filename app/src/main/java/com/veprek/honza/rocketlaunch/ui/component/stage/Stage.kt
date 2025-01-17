package com.veprek.honza.rocketlaunch.ui.component.stage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.veprek.honza.rocketlaunch.R
import com.veprek.honza.rocketlaunch.repository.model.Stage
import com.veprek.honza.rocketlaunch.ui.component.item.StageItem
import com.veprek.honza.rocketlaunch.ui.theme.RocketLaunchTheme
import com.veprek.honza.rocketlaunch.ui.theme.cornerRadius
import com.veprek.honza.rocketlaunch.ui.theme.lightGray
import com.veprek.honza.rocketlaunch.ui.theme.mediumPadding
import com.veprek.honza.rocketlaunch.ui.theme.smallPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Stage(
    modifier: Modifier = Modifier,
    name: String,
    stage: Stage
) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = lightGray
    ) {
        Column(modifier = modifier.padding(mediumPadding)) {
            Text(
                text = name,
                modifier = Modifier.padding(smallPadding),
                style = MaterialTheme.typography.h4
            )
            if (stage.reusable) {
                StageItem(
                    icon = painterResource(R.drawable.reusable),
                    name = stringResource(R.string.stage_reusable)
                )
            } else {
                StageItem(
                    icon = painterResource(R.drawable.reusable),
                    name = stringResource(R.string.stage_not_reusable)
                )
            }
            StageItem(
                icon = painterResource(R.drawable.engine),
                name = pluralStringResource(
                    id = R.plurals.stage_engine,
                    count = stage.engines,
                    stage.engines
                )
            )
            StageItem(
                icon = painterResource(R.drawable.fuel),
                name = pluralStringResource(
                    R.plurals.stage_fuel,
                    count = stage.fuelAmountTons,
                    stage.fuelAmountTons
                )
            )
            StageItem(
                icon = painterResource(R.drawable.burn),
                name = pluralStringResource(
                    R.plurals.stage_burn_time,
                    count = stage.burnTimeSec,
                    stage.burnTimeSec
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StagePreview(
    modifier: Modifier = Modifier,
    name: String = "First Stage",
    stage: Stage = Stage(true, 2, 193, 42)
) {
    RocketLaunchTheme {
        Stage(modifier, name, stage)
    }
}
