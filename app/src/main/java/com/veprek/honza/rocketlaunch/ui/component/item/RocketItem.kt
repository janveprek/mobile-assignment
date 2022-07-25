package com.veprek.honza.rocketlaunch.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.veprek.honza.rocketlaunch.R
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.ui.theme.iconPadding
import com.veprek.honza.rocketlaunch.ui.theme.iconSize
import com.veprek.honza.rocketlaunch.ui.theme.pink
import quanti.com.kotlinlog.Log

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RocketItem(rocket: Rocket, iconColor: Color = pink, detailAction: (String) -> Unit = {}) {
    ListItem(
        Modifier.fillMaxWidth().background(MaterialTheme.colors.background)
            .clickable {
                Log.d("Clicked")
                detailAction(rocket.id)
//
            },
        trailing = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Color.LightGray,
                contentDescription = stringResource(R.string.icon_next)
            )
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_rocket),
                tint = iconColor,
                modifier = Modifier.padding(
                    top = iconPadding,
                    bottom = iconPadding,
                    end = iconPadding
                ).size(iconSize),
                contentDescription = stringResource(R.string.icon_rocket)
            )
            Column() {
                Text(
                    rocket.name,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    stringResource(R.string.rocket_first_flight, rocket.firstFlight),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.typography.caption.color.copy(alpha = ContentAlpha.medium)
                )
            }
        }
    }
}
